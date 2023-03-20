package com.yjx.service;

import com.yjx.dal.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
public interface UserService extends IService<User> {
    User getUserByUsername(String username);
}
