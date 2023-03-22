package com.yjx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjx.dal.entity.UrlPermission;
import com.yjx.dal.mapper.UrlPermissionMapper;
import com.yjx.service.UrlPermissionService;
import org.springframework.stereotype.Service;

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

}
