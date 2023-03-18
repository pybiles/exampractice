package com.yjx.dal.mapper;

import com.yjx.dal.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
