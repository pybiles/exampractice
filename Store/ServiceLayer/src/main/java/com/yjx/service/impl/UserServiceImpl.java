package com.yjx.service.impl;

import com.yjx.dal.entity.User;
import com.yjx.dal.mapper.UserMapper;
import com.yjx.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
