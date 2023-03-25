package com.yjx.dal.mapper;

import com.yjx.dal.entity.ManagerRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Mapper
public interface ManagerRoleMapper extends BaseMapper<ManagerRole> {
    void batchInsert(Long managerId,Long[] roleIds);
}
