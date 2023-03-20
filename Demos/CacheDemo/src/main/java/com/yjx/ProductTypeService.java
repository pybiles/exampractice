package com.yjx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    public List<ProductType> getAll(){
        List<ProductType> productTypes = productTypeMapper.selectList(null);
        return productTypes;
    }

    public ProductType getById(Integer typeId){
        ProductType productType = productTypeMapper.selectById(typeId);
        return productType;
    }

    public void add(ProductType productType){
        int insert = productTypeMapper.insert(productType);
    }

    public void deleteById(Integer typeId){
        int i = productTypeMapper.deleteById(typeId);
    }

    public void updateById(ProductType productType){
        int i = productTypeMapper.updateById(productType);
    }


}
