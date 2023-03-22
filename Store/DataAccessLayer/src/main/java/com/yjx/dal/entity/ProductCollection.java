package com.yjx.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author yjx
 * @since 2023-03-22 07:20:39
 */
@Getter
@Setter
@TableName("t_product_collection")
public class ProductCollection {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 产品id
     */
    @TableField("product_id")
    private Integer productId;


}
