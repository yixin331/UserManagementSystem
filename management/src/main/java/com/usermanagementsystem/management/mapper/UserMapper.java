package com.usermanagementsystem.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.usermanagementsystem.management.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helen
 * @since 2023-07-28
 */
public interface UserMapper extends BaseMapper<User> {
    public List<String> getRoleNameByUserId(Integer userId);
}
