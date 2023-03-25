package com.yjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjx.dal.entity.Manager;
import com.yjx.dal.entity.ManagerRole;
import com.yjx.dal.entity.RoleUrlPermission;
import com.yjx.dal.mapper.ManagerMapper;
import com.yjx.dal.mapper.ManagerRoleMapper;
import com.yjx.dal.mapper.RoleUrlPermissionMapper;
import com.yjx.dal.model.PermissionMenu;
import com.yjx.dal.entity.UrlPermission;
import com.yjx.dal.mapper.UrlPermissionMapper;
import com.yjx.service.UrlPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单和接口权限定义 服务实现类
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Service
public class UrlPermissionServiceImpl extends ServiceImpl<UrlPermissionMapper, UrlPermission> implements UrlPermissionService {

    @Autowired
    UrlPermissionMapper urlPermissionMapper;

    @Autowired
    RoleUrlPermissionMapper roleUrlPermissionMapper;

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    ManagerRoleMapper managerRoleMapper;

    @Override
    public List<PermissionMenu> getAllRootMenuBySql() {
        List<PermissionMenu> menuList = urlPermissionMapper.selectAllRootMenu();
        return menuList;
    }

    @Override
    public List<PermissionMenu> getAllRootMenuByMap() {

        //查出所有数据,转换为List<PermissionMenu>
        List<UrlPermission> urlPermissions = urlPermissionMapper.selectList(null);

        return urlPermissionList2PermissionMenuList(urlPermissions);
    }

    private List<PermissionMenu> urlPermissionList2PermissionMenuList(List<UrlPermission> urlPermissions){

        List<PermissionMenu> permissionMenuList = urlPermissions.stream().map(urlPermission -> {
            PermissionMenu permissionMenu = new PermissionMenu();

            permissionMenu.setId(urlPermission.getId());
            permissionMenu.setName(urlPermission.getName());
            permissionMenu.setUrl(urlPermission.getUrl());
            permissionMenu.setParentId(urlPermission.getParentId());
            permissionMenu.setChildren(new ArrayList<>());

            return permissionMenu;
        }).collect(Collectors.toList());

        //转换为Map<PermissionMenu.id,PermissionMenu>
        HashMap<Long, PermissionMenu> permissionMenuHashMap = new HashMap<>();
        permissionMenuList.forEach(permissionMenu -> {
            permissionMenuHashMap.put(permissionMenu.getId(),permissionMenu);
        });

        //遍历Map,根据parentId找到父PermissionMenu,把自身放到父PermissionMenu的children
        permissionMenuHashMap.forEach( (id,permissionMenu) -> {
            //找到自己的父元素
            PermissionMenu parent = permissionMenuHashMap.get(permissionMenu.getParentId());
            if (parent!=null){
                parent.getChildren().add(permissionMenu);
            }
        });

        //过滤出一级菜单
        List<PermissionMenu> rootPermissionMenuList = permissionMenuHashMap.values().stream().filter(permissionMenu -> {
            return permissionMenu.getParentId() == -1;
        }).collect(Collectors.toList());

        return rootPermissionMenuList;
    }

    @Override
    public Page<UrlPermission> pageSearch(Integer currentPage, Integer pageSize, String keyword) {

        Page<UrlPermission> page = new Page<>(currentPage,pageSize);

        QueryWrapper<UrlPermission> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.like("name",keyword);
        }

        Page<UrlPermission> permissionPage = urlPermissionMapper.selectPage(page, queryWrapper);

        return permissionPage;
    }

    @Override
    @Transactional
    public void deleteById(Long urlPermissionId) {
        deleteAllRelation(urlPermissionId);
    }

    @Override
    public List<PermissionMenu> allOfCurrentUser(String username) {

        //查询出Manager
        QueryWrapper<Manager> managerQueryWrapper= new QueryWrapper<>();
        managerQueryWrapper.eq("user_name",username);
        Manager manager = managerMapper.selectOne(managerQueryWrapper);

        //查Manager关联的角色
        QueryWrapper<ManagerRole> managerRoleQueryWrapper = new QueryWrapper<>();
        managerRoleQueryWrapper.eq("manager_id",manager.getId());
        Set<Long> roleIdSet = managerRoleMapper.selectList(managerRoleQueryWrapper)
                .stream()
                .map(managerRole -> {
                    return managerRole.getRoleId();
                })
                .collect(Collectors.toSet());

        //角色关联的权限
        QueryWrapper<RoleUrlPermission> roleUrlPermissionQueryWrapper = new QueryWrapper<>();
        roleUrlPermissionQueryWrapper.in("role_id",roleIdSet);

        Set<Long> urlpermissionIdSet = roleUrlPermissionMapper.selectList(roleUrlPermissionQueryWrapper)
                .stream()
                .map(roleUrlPermission -> {
                    return roleUrlPermission.getUrlPermissionId();
                }).collect(Collectors.toSet());

        QueryWrapper<UrlPermission> urlPermissionQueryWrapper = new QueryWrapper<>();
        urlPermissionQueryWrapper.in("id",urlpermissionIdSet);

        List<UrlPermission> urlPermissionList = urlPermissionMapper.selectList(urlPermissionQueryWrapper);

        //转换为List<PermissionMenu>
        List<PermissionMenu> permissionMenuList = urlPermissionList2PermissionMenuList(urlPermissionList);

        return permissionMenuList;
    }

    private void deleteAllRelation(Long urlPermissionId){

        //删除子菜单
        QueryWrapper<UrlPermission> urlPermissionQueryWrapper = new QueryWrapper<>();
        urlPermissionQueryWrapper.eq("parent_id",urlPermissionId);
        List<UrlPermission> subPermissionList = urlPermissionMapper.selectList(urlPermissionQueryWrapper);
        for(UrlPermission urlPermission : subPermissionList){
            deleteAllRelation(urlPermission.getId());
        }


        //删除自身
        //删除权限表中的数据
        urlPermissionMapper.deleteById(urlPermissionId);
        //删除角色权限关联表中和当前权限相关的数据
        QueryWrapper<RoleUrlPermission> roleUrlPermissionQueryWrapper = new QueryWrapper<>();
        roleUrlPermissionQueryWrapper.eq("url_permission_id",urlPermissionId);
        roleUrlPermissionMapper.delete(roleUrlPermissionQueryWrapper);


    }


}

