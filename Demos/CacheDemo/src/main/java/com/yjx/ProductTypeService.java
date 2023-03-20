package com.yjx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    //执行该方法需要使用缓存   value别名cacheName,值得是组名
    @Cacheable(value = "productTypesDemo",key = "#root.targetClass+#root.methodName")
//    @Cacheable(cacheNames = "productTypesDemo",key = "#root.targetClass+#root.methodName")
    public List<ProductType> getAll() {

        List<ProductType> productTypes = productTypeMapper.selectList(null);

        return productTypes;
    }

    public ProductType getById(Integer typeId) {
        ProductType productType = productTypeMapper.selectById(typeId);
        return productType;
    }
    //移除缓存数据
    @CacheEvict(value ="productTypesDemo",allEntries = true)
    public void add(ProductType productType) {
        int insert = productTypeMapper.insert(productType);
    }
    @CacheEvict(value ="productTypesDemo",allEntries = true)
    public void deleteById(Integer typeId) {
        int i = productTypeMapper.deleteById(typeId);

    }
    @CacheEvict(value ="productTypesDemo",allEntries = true)
    public void updateById(ProductType productType) {
        int i = productTypeMapper.updateById(productType);
    }


}
