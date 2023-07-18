package com.usermanagementsystem.management.service.impl;

import com.usermanagementsystem.management.entity.User;
import com.usermanagementsystem.management.mapper.UserMapper;
import com.usermanagementsystem.management.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helen
 * @since 2023-07-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
