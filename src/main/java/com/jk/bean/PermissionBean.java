package com.jk.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionBean implements Serializable {

    private static final long serialVersionUID = 3257833648244774323L;
    private Integer id;
    private String name;//权限名字
    private String url;//权限字段
}
