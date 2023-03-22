package com.yjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjx.dal.entity.Manager;
import com.yjx.dal.mapper.ManagerMapper;
import com.yjx.service.ManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内部员工信息 服务实现类
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    @Override
    public Manager checkLogin(String username, String password) {
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        queryWrapper.eq("user_password", password);


        Manager manager = managerMapper.selectOne(queryWrapper);
        return manager;
    }

}

