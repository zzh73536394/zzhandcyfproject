package com.jk.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class RoleBean implements Serializable {

    private static final long serialVersionUID = -3802837197093382467L;
    private Integer id;
    private String name;//角色名字
    private Set<PermissionBean> permissions = new HashSet<>();
}
