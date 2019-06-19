package com.jk.service;

import com.jk.bean.Ossbean;
import com.jk.bean.UserBean;
import com.jk.bean.kecheng;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ZzhService {
    HashMap<String, Object> findoss(Integer page, Integer limit);

    void deleteBiaoti(String btid);

    void addLunbo(Ossbean ossbean);

    UserBean findByName(String username);

    HashMap<String, Object> findyonghu(Integer page, Integer limit);

    void deleteyonghu(Integer id);

    void updateLunbo(Ossbean ossbean);

    HashMap<String, Object> findkecheng(Integer page, Integer limit, kecheng kecheng);

    void updatekechengfenlei(kecheng kecheng);
}
