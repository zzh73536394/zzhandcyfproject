package com.jk.service;



import com.jk.bean.Inst;
import com.jk.bean.MenuTree;
import com.jk.bean.Teacher;
import com.jk.bean.Video;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ManageService {
    List<LinkedHashMap<String, Object>> getTree();

    List<MenuTree> getTreeAll();

    HashMap<String, Object> getTeacher(Integer page, Integer limit);

    Teacher getTeacherById(Integer id);

    void updateTeachcerStart(Integer id, Integer chec);

    HashMap<String, Object> getInst(Integer page, Integer limit);

    Inst getInstById(Integer id);

    void updateInstStart(Integer id, Integer start);

    void sendInst(Inst inst, Integer start);

    void sendTeacher(Teacher teacher, Integer chec);

    HashMap<String, Object> getVideo(Integer page, Integer limit);

    Video getVideoById(Integer id);

    void updateVideoStart(Integer id, Integer start);

    void sendVideoStart(Video video, Integer start);
}
