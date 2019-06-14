package com.jk.managementsystem.controller;


import com.jk.managementsystem.bean.MenuTree;
import com.jk.managementsystem.service.ManageService;
import com.jk.managementsystem.utils.TreeNoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        list =  TreeNoteUtil.getFatherNode(list);

        return list;
    }


    /*@RequestMapping("getTree")
    @ResponseBody
    public List<LinkedHashMap<String, Object>> getTree() {
        return manageService.getTree();
    }*/
}
