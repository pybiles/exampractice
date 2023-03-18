package com.yjx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjx.entity.User;
import com.yjx.mapper.UserMapper;
import com.yjx.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {


}