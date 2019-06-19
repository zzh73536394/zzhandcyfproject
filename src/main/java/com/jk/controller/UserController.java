package com.jk.controller;

import com.jk.bean.UserBean;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping("/user/index")
    public String add(HttpServletRequest request){
        UserBean bean = (UserBean) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("userName", bean.getName());
        return "/user/index";
    }

    /**
     * 会员中心，需认证且角色为vip可访问
     */
    @RequestMapping("/vip/index")
    public String update(){
        return "/vip/index";
    }

    @RequestMapping("/user/ossguanli")
    public String ossguanli(){
        return "/user/ossguanli";
    }

    @RequestMapping("/user/kechengfenlei")
    public String kechengfenlei() {
        return  "/user/kechengfenlei";
    }


}
