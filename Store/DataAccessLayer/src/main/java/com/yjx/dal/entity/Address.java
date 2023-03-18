package com.yjx.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author yjx
 * @since 2023-03-18 06:59:29
 */
@Getter
@Setter
@TableName("t_address")
public class Address {

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电话
     */
    @TableField("tel")
    private String tel;

    /**
     * 收货人姓名
     */
    @TableField("name")
    private String name;

    /**
     * 收货地址
     */
    @TableField("address")
    private String address;

    /**
     * 所属用户id
     */
    @TableField("user_id")
    private Integer userId;


}
