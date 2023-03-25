package com.yjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjx.dal.entity.Manager;
import com.yjx.dal.entity.ManagerRole;
import com.yjx.dal.mapper.ManagerMapper;
import com.yjx.dal.mapper.ManagerRoleMapper;
import com.yjx.service.ManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Autowired
    ManagerRoleMapper managerRoleMapper;

    @Override
    public Manager checkLogin(String username, String password) {
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        queryWrapper.eq("user_password",password);


        Manager manager = managerMapper.selectOne(queryWrapper);
        return manager;
    }


    @Override
    public Page<Manager> pageSearch(Integer currentPage, Integer pageSize, String keyword) {

        Page<Manager> page = new Page<>(currentPage,pageSize);

        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.like("user_name",keyword);
        }

        return managerMapper.selectPage(page,queryWrapper);

    }

    @Override
    @Transactional
    public void deleteManager(Long id) {

        managerMapper.deleteById(id);


        QueryWrapper<ManagerRole> managerRoleQueryWrapper = new QueryWrapper<>();
        managerRoleQueryWrapper.eq("manager_id",id);
        managerRoleMapper.delete(managerRoleQueryWrapper);

    }




}

