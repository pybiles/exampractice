package com.yjx.dal.mapper;

import com.yjx.dal.entity.RoleUrlPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色与菜单、接口权限的对应关系 Mapper 接口
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Mapper
public interface RoleUrlPermissionMapper extends BaseMapper<RoleUrlPermission> {
    void batchInsert(Long roleId,Long[] permissionIds);
}
