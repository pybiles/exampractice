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
 * @since 2023-03-19 10:23:51
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
     * 商品产地
     */
    @TableField("product_area")
    private String productArea;

    /**
     * 商品简介
     */
    @TableField("detail")
    private String detail;

    /**
     * 商品图片地址
     */
    @TableField("product_imgsrc")
    private String productImgsrc;

    /**
     * 阅读量
     */
    @TableField("read_count")
    private Integer readCount;

    /**
     * 购买量
     */
    @TableField("buy_count")
    private Integer buyCount;

    /**
     * 收藏量
     */
    @TableField("collect_count")
    private Integer collectCount;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;


}