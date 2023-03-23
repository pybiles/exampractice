package com.yjx.dal.model;

import lombok.Data;

import java.util.List;
@Data
public class PermissionMenu {
    private Long id;
    private String name;
    private String url;
    private Long parentId;

    private List<PermissionMenu> children;
}
