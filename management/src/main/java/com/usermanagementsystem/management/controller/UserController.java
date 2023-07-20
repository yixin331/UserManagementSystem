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
 * @since 2023-07-17
 */
@RestController
@RequestMapping("/user")
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
        return Result.fail(40001, "Incorrect username or password");
    }

}
