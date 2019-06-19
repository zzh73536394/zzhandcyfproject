package com.jk.service;


import com.jk.bean.Inst;
import com.jk.bean.MenuTree;
import com.jk.bean.Teacher;
import com.jk.bean.Video;
import com.jk.dao.ManageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ManageServiceImpl implements ManageService{

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private CreateHtml createHtml;


    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());





    @Override
    public List<LinkedHashMap<String, Object>> getTree() {
        return manageMapper.getTree();
    }

    @Override
    public List<MenuTree> getTreeAll() {
        List<MenuTree> list = manageMapper.getTreeAll();
        return list;
    }

    @Override
    public HashMap<String, Object> getTeacher(Integer page, Integer limit) {
        long count = manageMapper.getTeacher();
        List<Teacher> list = manageMapper.getTeacherAll((page-1)*limit,limit);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("count",count);
        hashMap.put("data",list);
        hashMap.put("code",0);
        return hashMap;
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        return manageMapper.getTeacherById(id);
    }

    @Override
    public void updateTeachcerStart(Integer id, Integer chec) {
        manageMapper.updateTeachcerStart(id,chec);
    }

    @Override
    public HashMap<String, Object> getInst(Integer page, Integer limit) {
        long count = manageMapper.getInst();
        List<Inst> list = manageMapper.getInstAll((page-1)*limit,limit);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("count",count);
        hashMap.put("data",list);
        hashMap.put("code",0);
        return hashMap;

    }

    @Override
    public Inst getInstById(Integer id) {
        return manageMapper.getInstById(id);
    }

    @Override
    public void updateInstStart(Integer id, Integer start) {
        manageMapper.updateInstStart(id,start);
    }

    @Override
    public void sendInst(Inst inst, Integer start) {
        String text= "";
        if (start == 2) {
            text +="你好"+inst.getInstName()+",您在网易云课堂提交的审核已通过.";
        }
        if (start == 3) {
            text +="你好"+inst.getInstName()+",您在网易云课堂提交的审核因为' "+inst.getErrorMsg()+" '原因未通过,请从新提交申请.";
        }


        System.out.println(inst.getEmail());
        System.out.println(text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(inst.getEmail());
        message.setSubject("通知信息");
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void sendTeacher(Teacher teacher, Integer chec) {

        String text= "";
        if (chec == 2) {
            text +="你好"+teacher.getTeacherName()+",您在网易云课堂提交的已通过审核.";
        }
        if (chec == 3) {
            text +="你好"+teacher.getTeacherName()+",您在网易云课堂提交的审核因为' "+teacher.getErrorMsg()+" '原因未能通过,请从新提交申请.";
        }


        System.out.println(teacher.getEmail());
        System.out.println(text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(teacher.getEmail());
        message.setSubject("通知信息");
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public HashMap<String, Object> getVideo(Integer page, Integer limit) {
        long count = manageMapper.getVideo();
        List<Video> list = manageMapper.getVideoAll((page-1)*limit,limit);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("count",count);
        hashMap.put("data",list);
        hashMap.put("code",0);
        return hashMap;
    }

    @Override
    public Video getVideoById(Integer id) {
        return manageMapper.getVideoById(id);
    }

    @Override
    public void updateVideoStart(Integer id, Integer start) {
        manageMapper.updateVideoStart(id,start);
    }

    @Override
    public void sendVideoStart(Video video, Integer start) {

        Teacher teacher = manageMapper.getTeacherById(video.getTeacherId());

        String text= "";
        if (start == 2) {
            text +="你好"+teacher.getTeacherName()+",您在网易云课堂提交的"+video.getVideoName()+"视频已通过审核.";

            Boolean b = createHtml.saveProduct(video.getId());
            System.out.println(b+"===================");
        }
        if (start == 1) {
            text +="你好"+teacher.getTeacherName()+",您在网易云课堂提交的"+video.getVideoName()+"视频因为' "+video.getErrorMsg()+" '原因未能通过审核,请从新提交申请.";
        }


        System.out.println(teacher.getEmail());
        System.out.println(text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(teacher.getEmail());
        message.setSubject("通知信息");
        message.setText(text);
        mailSender.send(message);
    }
}
