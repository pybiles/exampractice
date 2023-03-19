package com.yjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjx.dal.entity.Product;
import com.yjx.dal.mapper.ProductMapper;
import com.yjx.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> topN(Integer n) {
        //分页查询条件
        Page<Product> page = new Page<>(1, n);

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.orderByDesc("buy_count");

        Page<Product> productPageResult = productMapper.selectPage(page, productQueryWrapper);
        List<Product> productList = productPageResult.getRecords();

        return productList;
    }

    @Override
    public Product seeDetail(Integer productId) {
        Product product = productMapper.selectById(productId);
//阅读量+1
        Product productUpdate = new Product();
        productUpdate.setId(product.getId());
        productUpdate.setReadCount(product.getReadCount()+1 );

        productMapper.updateById(productUpdate);

        return product;
    }

    @Override
    public Page<Product> pageByType(Integer currentPage, Integer pageSize, Integer typeId) {
        //分页查询条件
        Page<Product> page = new Page<>(currentPage,pageSize);

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_type_id",typeId);
        productQueryWrapper.orderByDesc("buy_count");

        Page<Product> productPageResult = productMapper.selectPage(page, productQueryWrapper);

        return productPageResult;
    }
}
