package com.yjx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjx.dal.model.PermissionMenu;
import com.yjx.dal.entity.UrlPermission;
import com.yjx.dal.mapper.UrlPermissionMapper;
import com.yjx.service.UrlPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<PermissionMenu> getAllRootMenuBySql() {
        List<PermissionMenu> menuList = urlPermissionMapper.selectAllRootMenu();
        return menuList;
    }
}
