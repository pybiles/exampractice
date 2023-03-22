package com.yjx.service.impl;

import com.yjx.dal.entity.RoleUrlPermission;
import com.yjx.dal.mapper.RoleUrlPermissionMapper;
import com.yjx.service.RoleUrlPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单、接口权限的对应关系 服务实现类
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Service
public class RoleUrlPermissionServiceImpl extends ServiceImpl<RoleUrlPermissionMapper, RoleUrlPermission> implements RoleUrlPermissionService {

}
