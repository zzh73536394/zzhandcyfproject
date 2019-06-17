package com.jk.service;


import com.jk.bean.MenuTree;
import com.jk.bean.Teacher;
import com.jk.dao.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ManageServiceImpl implements ManageService{

    @Autowired
    private ManageMapper manageMapper;

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
}
