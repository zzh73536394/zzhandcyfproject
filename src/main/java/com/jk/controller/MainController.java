package com.jk.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    //主页面
    @RequestMapping("/main")
    public String index(HttpServletRequest request, HttpServletResponse response){
        //response.setHeader("root", request.getContextPath());
        return "zhu";
    }

    //登陆页面
    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        //response.setHeader("root", request.getContextPath());
        return "login";
    }

    //点击登录后获取的前台信息
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        //获取到请求的头名字  freemarker模板时使用
        //response.setHeader("root", request.getContextPath());
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 3.执行登录方法
        try{
            subject.login(token);
            boolean isAuthenticated = subject.isAuthenticated();
            System.out.println("是否认证通过: " + isAuthenticated);
            return "redirect:/main";
        } catch (UnknownAccountException e){
            e.printStackTrace();
            request.setAttribute("msg","用户名不存在！");
            boolean isAuthenticated = subject.isAuthenticated();
            System.out.println("是否认证通过: " + isAuthenticated);
        } catch (IncorrectCredentialsException e){
            request.setAttribute("msg","密码错误！");
            boolean isAuthenticated = subject.isAuthenticated();
            System.out.println("是否认证通过: " + isAuthenticated);
        }
        return "demo";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/main";
    }

    //未授权提示页面
    @RequestMapping("/error/unAuth")
    public String unAuth(){
        return "/error/unAuth";
    }

}
