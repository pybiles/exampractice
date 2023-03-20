package com.yjx;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * 商品类型表
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Getter
@Setter
@TableName("t_product_type")
public class ProductType {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型名
     */
    @TableField("type_name")
    private String typeName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}