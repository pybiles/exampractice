package com.yjx.front.controller;


import com.yjx.dal.entity.ProductType;
import com.yjx.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品类型表 前端控制器
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    RedisTemplate<String,Object> stringObjectRedisTemplate;
    @RequestMapping("/all")
    public List<ProductType> all(){

        //增加缓存机制
        ValueOperations<String, Object> opsForValue = stringObjectRedisTemplate.opsForValue();
        List<ProductType> productTypeList = (List<ProductType>)opsForValue.get("productTypeList");
        if (productTypeList == null){
            productTypeList = productTypeService.list();
            opsForValue.set("productTypeList",productTypeList);
        }

        return productTypeList;
    }
    @RequestMapping("detail")
    public ProductType detail(Integer productTypeId){
        return productTypeService.getById(productTypeId);
    }
}

