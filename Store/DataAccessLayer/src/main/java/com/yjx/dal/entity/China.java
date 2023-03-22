package com.yjx.dal.entity;

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
@TableName("t_china")
public class China {

    @TableId("Id")
    private Integer id;

    /**
     * 所在地名
     */
    @TableField("Name")
    private String name;

    @TableField("Pid")
    private Integer pid;


}
