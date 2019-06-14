package com.jk.dao;

import com.jk.bean.MenuTree;
import com.jk.bean.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;


public interface ManageMapper {
    @Select("Select * from t_tree")
    List<LinkedHashMap<String, Object>> getTree();
    @Select("select * from t_tree")
    List<MenuTree> getTreeAll();
    @Select("select count(*) from t_teacher")
    long getTeacher();

    List<Teacher> getTeacherAll(@Param("page") Integer page,@Param("limit") Integer limit);
}
