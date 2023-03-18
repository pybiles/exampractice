package com.yjx.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Getter
@Setter
@TableName("t_order")
public class Order {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField("order_num")
    private String orderNum;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 收货地址id
     */
    @TableField("address_id")
    private Integer addressId;

    /**
     * 状态(1未支付,2已支付,3已取消) 
     */
    @TableField("status")
    private Integer status;

    /**
     * 订单总额
     */
    @TableField("totalprice")
    private BigDecimal totalprice;


}
