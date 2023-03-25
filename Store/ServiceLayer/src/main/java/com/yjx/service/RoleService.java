package com.yjx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjx.dal.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
public interface RoleService extends IService<Role> {
    Page<Role> pageSearch(Integer currentPage, Integer pageSize, String keyword);

    void deleteRole(Long id);
    Set<Long> getRelationPermissionIds(Integer roleId);

    void refreshRelationPermissionIds(Long roleId, Long[] permissionIds);
}
