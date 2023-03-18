package com.yjx.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

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


}
