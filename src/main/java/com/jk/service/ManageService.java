package com.jk.service;



import com.jk.bean.MenuTree;
import com.jk.bean.Teacher;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ManageService {
    List<LinkedHashMap<String, Object>> getTree();

    List<MenuTree> getTreeAll();

    HashMap<String, Object> getTeacher(Integer page, Integer limit);

    Teacher getTeacherById(Integer id);

    void updateTeachcerStart(Integer id, Integer chec);
}
