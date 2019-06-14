package com.jk.controller;


import com.jk.bean.MenuTree;
import com.jk.bean.Teacher;
import com.jk.service.ManageService;
import com.jk.util.TreeNoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class ManageController {

    @Autowired
    ManageService manageService;

    @RequestMapping("toShow")
    public String toShow(String url) {
        return url;
    }

    /**
     * 左侧导航树
     * @return
     */
    @RequestMapping("getTreeAll")
    @ResponseBody
    public List<MenuTree> getTreeAll(){

        List<MenuTree> list = manageService.getTreeAll();
        System.out.println("222");
        list =  TreeNoteUtil.getFatherNode(list);

        return list;
    }

    /**
     * 教师注册审核
     */
    @RequestMapping("getTeacher")
    @ResponseBody
    public HashMap<String, Object> getTeacher(Integer page, Integer limit){
        HashMap<String, Object> list = manageService.getTeacher(page,limit);
        return list;
    }


}
