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
 * 订单明细表
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Getter
@Setter
@TableName("t_order_item")
public class OrderItem {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 商品名
     */
    @TableField("product_name")
    private String productName;

    /**
     * 商品单价
     */
    @TableField("product_price")
    private BigDecimal productPrice;

    /**
     * 商品图片地址
     */
    @TableField("product_imgsrc")
    private String productImgsrc;

    /**
     * 购买量
     */
    @TableField("buycount")
    private Integer buycount;

    /**
     * 小计
     */
    @TableField("sumprice")
    private BigDecimal sumprice;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;


}
