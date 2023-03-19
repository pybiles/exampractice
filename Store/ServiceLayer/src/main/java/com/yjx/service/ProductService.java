package com.yjx.service;

import com.yjx.dal.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
public interface ProductService extends IService<Product> {
    List<Product> topN(Integer n);
}
