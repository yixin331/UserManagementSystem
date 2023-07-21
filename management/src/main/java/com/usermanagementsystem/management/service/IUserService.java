package com.usermanagementsystem.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usermanagementsystem.management.entity.User;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helen
 * @since 2023-07-21
 */
public interface IUserService extends IService<User> {
    Map<String,Object> login(User user);

    Map<String,Object> getUserInfo(String token);
}
