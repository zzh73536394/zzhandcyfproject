package com.jk.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserBean implements Serializable {

    private static final long serialVersionUID = -6380679827539254303L;
    private Integer id;
    private String name;//用户名
    private String password;//密码
    private Set<RoleBean> roles = new HashSet<>();
}
