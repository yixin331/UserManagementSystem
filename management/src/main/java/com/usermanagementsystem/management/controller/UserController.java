package com.usermanagementsystem.management.controller;

import com.usermanagementsystem.common.Result;
import com.usermanagementsystem.management.entity.User;
import com.usermanagementsystem.management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helen
 * @since 2023-07-21
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

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

}
