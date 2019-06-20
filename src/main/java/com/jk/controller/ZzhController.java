package com.jk.controller;

import com.jk.aop.annotation;
import com.jk.bean.Ossbean;
import com.jk.bean.kecheng;
import com.jk.bean.weblog;
import com.jk.service.ZzhService;
import com.jk.util.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/max")
public class ZzhController {

    @Autowired
    private ZzhService zzhService;

    //跳转页面
    @RequestMapping("toShow")
    public String toShow(String url) {
        return url;
    }

    //轮播图查询
    @annotation.MyLog(value = "查看了轮播图")
    @RequestMapping("findoss")
    @ResponseBody
    public HashMap<String, Object> findoss(Integer page, Integer limit) {
        HashMap<String, Object> findoss = zzhService.findoss(page, limit);
        return findoss;
    }

    @annotation.MyLog(value = "删除了一条轮播图")
    @RequestMapping("deleteBiaoti")
    @ResponseBody
    public void deleteBiaoti(String btid) {
        zzhService.deleteBiaoti(btid);
    }


    @RequestMapping("updaloadImg")
    @ResponseBody
    public String uploadImg(MultipartFile imgg) throws IOException {
        if (imgg == null || imgg.getSize() <= 0) {
            throw new IOException("file不能为空");
        }
        OSSClientUtil ossClient = new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(imgg);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //System.out.println(split[0]);
        return split[0];
    }

    //修改或新增轮播图
    @annotation.MyLog(value = "新增轮播图")
    @RequestMapping("adduser")
    @ResponseBody
    public HashMap<String, Object> adduser(Ossbean ossbean) {
        Integer id = ossbean.getId();
        HashMap<String, Object> map = new HashMap<>();

        if (id == null) {
          map = zzhService.addLunbo(ossbean);//新增轮播图

        } else {
            zzhService.updateLunbo(ossbean);

        }
            return map;
    }

    //超级管理员管理用户
    @annotation.MyLog(value = "查看管理员用户")
    @RequestMapping("findyonghu")
    @ResponseBody
    public HashMap<String, Object> findyonghu(Integer page, Integer limit) {
        return zzhService.findyonghu(page,limit);
    }

    @annotation.MyLog(value = "删除管理员")
    @RequestMapping("deleteyonghu")
    @ResponseBody
    public void deleteyonghu(Integer id) {
        zzhService.deleteyonghu(id);
    }

    @annotation.MyLog(value = "查询课程")
    @RequestMapping("findkecheng")
    @ResponseBody
    public HashMap<String, Object> findkecheng(Integer page, Integer limit, kecheng kecheng) {
        HashMap<String, Object> findkecheng = zzhService.findkecheng(page, limit, kecheng);
        return findkecheng;
    }

    @annotation.MyLog(value = "修改了课程分类")
    @RequestMapping("updatekechengfenlei")
    @ResponseBody
    public void updatekechengfenlei(kecheng kecheng) {
        zzhService.updatekechengfenlei(kecheng);
    }

    @annotation.MyLog(value = "显示隐藏轮播图")
    @RequestMapping("updatelunbostatus")
    @ResponseBody
    public HashMap<String, Object> updatelunbostatus(Integer id, Integer status) {
        HashMap<String, Object> updatelunbostatus = zzhService.updatelunbostatus(id, status);
        return updatelunbostatus;
    }

    @RequestMapping("findlog")
    @ResponseBody
    public HashMap<String, Object> findlog(Integer page, Integer limit, weblog weblog) {
        HashMap<String, Object> findlog = zzhService.findlog(page, limit,weblog);
        return findlog;
    }
}
