package com.yjx.dal.mapper;

import com.yjx.dal.model.PermissionMenu;
import com.yjx.dal.entity.UrlPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单和接口权限定义 Mapper 接口
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Mapper
public interface UrlPermissionMapper extends BaseMapper<UrlPermission> {
    List<PermissionMenu> selectAllRootMenu();
}
