package com.yjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjx.dal.entity.User;
import com.yjx.dal.mapper.UserMapper;
import com.yjx.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {

        if (StringUtils.isEmpty(username)) {
            return null;
        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);

        User user = userMapper.selectOne(userQueryWrapper);

        return user;
    }
}

