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

import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<Long> getRelationRoleIds(Integer managerId) {

        QueryWrapper<ManagerRole> managerRoleQueryWrapper = new QueryWrapper<>();
        managerRoleQueryWrapper.eq("manager_id",managerId);

        Set<Long> roleIdSet = managerRoleMapper.selectList(managerRoleQueryWrapper)
                .stream()
                .map(managerRole -> {
                    return managerRole.getRoleId();
                })
                .collect(Collectors.toSet());

        return roleIdSet;
    }

    @Override
    public void refreshRelationRoleIds(Long managerId, Long[] roleIds) {

        QueryWrapper<ManagerRole> managerRoleQueryWrapper = new QueryWrapper<>();
        managerRoleQueryWrapper.eq("manager_id",managerId);
        managerRoleMapper.delete(managerRoleQueryWrapper);

        if (roleIds!=null && roleIds.length>0){
            managerRoleMapper.batchInsert(managerId,roleIds);
        }


    }

}

