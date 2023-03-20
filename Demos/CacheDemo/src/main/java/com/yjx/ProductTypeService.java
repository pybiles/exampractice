package com.yjx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;
    private List<ProductType> productTypes = null;

    public List<ProductType> getAll() {
        if (productTypes == null) {
            productTypes = productTypeMapper.selectList(null);
            System.out.println("缓存为空, 从数据库中查询数据");
        } else {
            System.out.println("缓存中有值,直接返回缓存数据");
        }
        return productTypes;
    }

    public ProductType getById(Integer typeId) {
        ProductType productType = productTypeMapper.selectById(typeId);
        return productType;
    }

    public void add(ProductType productType) {
        int insert = productTypeMapper.insert(productType);
        productTypes = null;
    }

    public void deleteById(Integer typeId) {
        int i = productTypeMapper.deleteById(typeId);
        productTypes = null;
    }

    public void updateById(ProductType productType) {
        int i = productTypeMapper.updateById(productType);
        productTypes = null;
    }


}
