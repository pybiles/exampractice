package com.yjx.service.impl;

import com.yjx.dal.entity.Address;
import com.yjx.dal.mapper.AddressMapper;
import com.yjx.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
