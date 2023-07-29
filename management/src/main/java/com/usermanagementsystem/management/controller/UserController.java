package com.usermanagementsystem.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.usermanagementsystem.common.Result;
import com.usermanagementsystem.management.entity.User;
import com.usermanagementsystem.management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helen
 * @since 2023-07-28
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public Result<List<User>> getAllUsers(){
        List<User> userList = userService.list();
        return Result.success(userList);
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user){
        Map<String, Object> data = userService.login(user);
        if (data != null){
            return Result.success(data);
        }
        return Result.fail(40001, "Incorrect username or password.");
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token){
        Map<String, Object> data = userService.getUserInfo(token);
        if (data != null){
            return Result.success(data);
        }
        return Result.fail(40002, "Invalid login infomation. Please login again");
    }

    @PostMapping("/logout")
    public Result<Map<String, Object>> logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<Map<String, Object>> getUserList(@RequestParam(value = "username", required = false) String username,
                                                   @RequestParam(value = "email", required = false) String email,
                                                   @RequestParam(value = "pageNo") long pageNo,
                                                   @RequestParam(value = "pageSize") long pageSize){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(username), User::getUsername, username);
        wrapper.like(StringUtils.hasLength(email), User::getEmail, email);
        wrapper.orderByAsc(User::getUserid);
        Page<User> page = new Page<>(pageNo, pageSize);
        userService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userService.save(user);

            return Result.success("Successfully adding the new user!");
        }
        catch(Exception e) {
            return Result.fail("Username has been used. Please use another one.");
        }

    }

    @PutMapping
    public Result<?> updateUser(@RequestBody User user){
        user.setPassword(null);

        try {
            userService.updateById(user);

            return Result.success("Successfully updating the user!");
        }
        catch(Exception e) {
            return Result.fail("Username has been used. Please use another one.");
        }

    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id){
        userService.removeById(id);
        return Result.success("Successfully deleting the user!");
    }

}
