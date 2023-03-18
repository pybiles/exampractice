package com.yjx.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Getter
@Setter
@TableName("t_product")
public class Product {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品类型id
     */
    @TableField("product_type_id")
    private Integer productTypeId;

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
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;


}
