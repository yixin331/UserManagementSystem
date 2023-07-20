package com.usermanagementsystem.management.service;

import com.usermanagementsystem.management.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helen
 * @since 2023-07-17
 */
public interface IUserService extends IService<User> {

    Map<String,Object> login(User user);
}
