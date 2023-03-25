package com.yjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjx.dal.entity.ManagerRole;
import com.yjx.dal.entity.Role;
import com.yjx.dal.entity.RoleUrlPermission;
import com.yjx.dal.mapper.ManagerRoleMapper;
import com.yjx.dal.mapper.RoleMapper;
import com.yjx.dal.mapper.RoleUrlPermissionMapper;
import com.yjx.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleUrlPermissionMapper roleUrlPermissionMapper;

    @Autowired
    ManagerRoleMapper managerRoleMapper;
    @Override
    public Page<Role> pageSearch(Integer currentPage, Integer pageSize, String keyword) {
        Page<Role> page = new Page<>(currentPage,pageSize);

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.like("name",keyword)
                    .or()
                    .like("code",keyword);
        }

        return roleMapper.selectPage(page,queryWrapper);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {

        roleMapper.deleteById(id);

        QueryWrapper<RoleUrlPermission> roleUrlPermissionQueryWrapper = new QueryWrapper<>();
        roleUrlPermissionQueryWrapper.eq("role_id",id);
        roleUrlPermissionMapper.delete(roleUrlPermissionQueryWrapper);

        QueryWrapper<ManagerRole> managerRoleQueryWrapper = new QueryWrapper<>();
        managerRoleQueryWrapper.eq("role_id",id);
        managerRoleMapper.delete(managerRoleQueryWrapper);

    }

    @Override
    public Set<Long> getRelationPermissionIds(Integer roleId) {

        QueryWrapper<RoleUrlPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);

        List<RoleUrlPermission> roleUrlPermissionList = roleUrlPermissionMapper.selectList(queryWrapper);
        Set<Long> relationPermissionIds = roleUrlPermissionList.stream().map(roleUrlPermission -> {
            return roleUrlPermission.getUrlPermissionId();
        }).collect(Collectors.toSet());

        return relationPermissionIds;
    }

    @Override
    @Transactional
    public void refreshRelationPermissionIds(Long roleId, Long[] permissionIds) {

        //删除已有的
        QueryWrapper<RoleUrlPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        roleUrlPermissionMapper.delete(queryWrapper);

        //写入新的
        if (permissionIds!=null && permissionIds.length>0){
            roleUrlPermissionMapper.batchInsert(roleId,permissionIds);
        }

    }

}
