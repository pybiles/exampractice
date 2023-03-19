package com.yjx.front.controller;


import com.yjx.dal.entity.ProductType;
import com.yjx.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/all")
    public List<ProductType> all(){
        return productTypeService.list();
    }

}

