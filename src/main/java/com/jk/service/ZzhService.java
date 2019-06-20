package com.jk.service;

import com.jk.bean.Ossbean;
import com.jk.bean.UserBean;
import com.jk.bean.kecheng;
import com.jk.bean.weblog;

import java.util.HashMap;

public interface ZzhService {
    HashMap<String, Object> findoss(Integer page, Integer limit);

    void deleteBiaoti(String btid);

    HashMap<String, Object> addLunbo(Ossbean ossbean);

    UserBean findByName(String username);

    HashMap<String, Object> findyonghu(Integer page, Integer limit);

    void deleteyonghu(Integer id);

    void updateLunbo(Ossbean ossbean);

    HashMap<String, Object> findkecheng(Integer page, Integer limit, kecheng kecheng);

    void updatekechengfenlei(kecheng kecheng);

    HashMap<String, Object> updatelunbostatus(Integer id, Integer status);

    void save(weblog sysLog);

    HashMap<String, Object> findlog(Integer page, Integer limit, weblog weblog);
}
