package com.jk.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.aop.annotation;
import com.jk.bean.*;
import com.jk.service.ManageService;
import com.jk.util.ConstanConf;
import com.jk.util.HttpClientUtil;
import com.jk.util.Md5Util;
import com.jk.util.TreeNoteUtil;


import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import java.util.HashMap;

import java.util.List;

@Controller
public class ManageController {

    @Autowired
    ManageService manageService;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取当前登陆的名字
     * @return
     */
    @RequestMapping("getUser")
    @ResponseBody
    public UserBean getUser() {
        UserBean loginAccount = (UserBean) SecurityUtils.getSubject().getPrincipal();

       /* loginAccount.getName();*/

        return loginAccount;
    }


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
    @annotation.MyLog(value = "查看了审核未通过的教师页面")
    @RequestMapping("getTeacher")
    @ResponseBody
    public HashMap<String, Object> getTeacher(Integer page, Integer limit){
        HashMap<String, Object> list = manageService.getTeacher(page,limit);
        return list;
    }

    /**
     * 根据ID查询老师
     */
    @RequestMapping("getTeacherById")
    @ResponseBody
    public Teacher getTeacherById(Integer id) {
        return manageService.getTeacherById(id);
    }

    /**
     * 审核老师
     * @param teacher
     * @param chec
     * @return
     */
    @annotation.MyLog(value = "对教师资质审核")
    @RequestMapping("updateTeacherStart")
    @ResponseBody
    public String updateTeacherStart(Teacher teacher,Integer chec) {

        /*HashMap<String, Object> params = new HashMap<>();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String text= "";
        if (chec == 2) {
            text +="你好"+teacher.getTeacherName()+",您在网易云课堂提交的审核已通过";
        }
        if (chec == 3) {
            text +="你好"+teacher.getTeacherName()+",您在网易云课堂提交的审核因为' "+teacher.getErrorMsg()+" '原因未通过,请从新提交申请";
        }

        params.put("accountSid", ConstanConf.ACCOUNTSID);


        String sig = Md5Util.calc(ConstanConf.ACCOUNTSID+ConstanConf.AUTH_TOKEN+timestamp);
        params.put("sig", sig);
        params.put("templateid",ConstanConf.TEMPLATEID);


        params.put("accountSid", ConstanConf.ACCOUNTSID);
        params.put("phone",teacher.getTeacherPhone());
        params.put("msg",text);

        params.put("timestamp", timestamp);
        System.out.println(params.get("timestamp"));
        System.out.println(params.get("phone"));
        System.out.println(params.get("msg"));
        params.put("templateid",ConstanConf.TEMPLATEID);

        String post = HttpClientUtil.post(ConstanConf.SMS_URL,params);
        System.out.println(post);
        JSONObject parseObject = JSON.parseObject(post);
        String respCode = parseObject.getString("respCode");
        System.out.println(respCode);

        String str = "";
        if (!ConstanConf.SMS_SUCCESS.equals("00000")) {
            System.out.println("成功");
            str = "通知成功";
        } else {
            System.out.println("失败");
            str = "通知失败";
        }*/
        manageService.updateTeachcerStart(teacher.getId(),chec);
        return null;
    }

    /**
     * 邮件通知老师审核是否通过
     * @param teacher
     * @param chec
     * @return
     */
    @annotation.MyLog(value = "向教师邮箱发送审核结果")
    @RequestMapping("sendTeacherStart")
    @ResponseBody
    public String sendTeacherStart(Teacher teacher,Integer chec) {
        manageService.sendTeacher(teacher,chec);
        return null;
    }

    /**
     * 查询机构所有数据
     * @param page
     * @param limit
     * @return
     */
    @annotation.MyLog(value = "查看了审核未通过的机构")
    @RequestMapping("getInst")
    @ResponseBody
    public HashMap<String, Object> getInst(Integer page, Integer limit){
        HashMap<String, Object> list = manageService.getInst(page,limit);
        return list;
    }

    /**
     * 根据ID查询机构详细情况
     * @param id
     * @return
     */
    @RequestMapping("getInstById")
    @ResponseBody
    public Inst getInstById(Integer id) {
        return manageService.getInstById(id);
    }

    /**
     * 审核机构修改状态
     * @param inst
     * @param start
     * @return
     */
    @annotation.MyLog(value = "对机构做了审核")
    @RequestMapping("updateInstStart")
    @ResponseBody
    public String updateInstStart(Inst inst, Integer start) {

        manageService.updateInstStart(inst.getId(),start);
        return null;
    }

    /**
     * 通知审核机构是否通过
     * @param inst
     * @param start
     * @return
     */
    @annotation.MyLog(value = "向机构邮箱发送审核结果")
    @RequestMapping("sendInstStart")
    @ResponseBody
    public String sendInstStart(Inst inst, Integer start) {
        manageService.sendInst(inst, start);
        return null;
    }

    /**
     *查询视频 及 讲师
     * @param page
     * @param limit
     * @return
     */
    @annotation.MyLog(value = "查看了审核未通过的视频")
    @RequestMapping("getVideo")
    @ResponseBody
    public HashMap<String, Object> getVideo(Integer page, Integer limit){
        HashMap<String, Object> list = manageService.getVideo(page,limit);
        return list;
    }

    /**
     * 审核 视频的详细信息
     * @param id
     * @return
     */

    @RequestMapping("getVideoById")
    @ResponseBody
    public Video getVideoById(Integer id){
        return manageService.getVideoById(id);
    }

    /**
     * 修改视频审核状态
     * @param video
     * @param start
     * @return
     */
   // @annotation.MyLog(value = "对视频做了审核")
    @RequestMapping("updateVideoStart")
    @ResponseBody
    public String updateVideoStart(Video video,Integer start){
        manageService.updateVideoStart(video.getId(),start);
        return null;
    }
   // @annotation.MyLog(value = "向提交视频的教师邮箱发送视频的审核结果")
    @RequestMapping("sendVideoStart")
    @ResponseBody
    public String sendVideoStart(Video video, Integer start) {
        manageService.sendVideoStart(video, start);
        return null;
    }

}
