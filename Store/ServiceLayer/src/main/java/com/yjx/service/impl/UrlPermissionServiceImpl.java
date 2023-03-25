package com.yjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjx.dal.mapper.RoleUrlPermissionMapper;
import com.yjx.dal.model.PermissionMenu;
import com.yjx.dal.entity.UrlPermission;
import com.yjx.dal.mapper.UrlPermissionMapper;
import com.yjx.service.UrlPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<PermissionMenu> getAllRootMenuBySql() {
        List<PermissionMenu> menuList = urlPermissionMapper.selectAllRootMenu();
        return menuList;
    }

    @Override
    public List<PermissionMenu> getAllRootMenuByMap() {
        //查出所有数据,转换为List<PermissionMenu>
        List<UrlPermission> urlPermissions = urlPermissionMapper.selectList(null);
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
    public void deleteById(Long urlPermissionId) {

    }
}
