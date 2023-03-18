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
 * 购物车表
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Getter
@Setter
@TableName("t_cart")
public class Cart {

    /**
     * 自定id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 购买量
     */
    @TableField("buycount")
    private Integer buycount;

    /**
     * 小计(商品单价x购买量)
     */
    @TableField("sumprice")
    private BigDecimal sumprice;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;


}
