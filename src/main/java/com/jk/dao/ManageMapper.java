package com.jk.dao;

import com.jk.bean.Inst;
import com.jk.bean.MenuTree;
import com.jk.bean.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.LinkedHashMap;
import java.util.List;


public interface ManageMapper {
    @Select("Select * from t_tree")
    List<LinkedHashMap<String, Object>> getTree();
    @Select("select * from t_tree")
    List<MenuTree> getTreeAll();
    @Select("select count(*) from t_teacher")
    long getTeacher();

    List<Teacher> getTeacherAll(@Param("page") Integer page, @Param("limit") Integer limit);
    @Select("select * from t_teacher where id = #{id}")
    Teacher getTeacherById(@Param("id") Integer id);
    @Update("update t_teacher set chec = #{chec} where id = #{id}")
    void updateTeachcerStart(@Param("id") Integer id, @Param("chec") Integer chec);
    @Select("select count(*) from t_institutions")
    long getInst();

    List<Inst> getInstAll(@Param("page") Integer page,@Param("limit") Integer limit);
    @Select("select * from t_institutions where id = #{id}")
    Inst getInstById(@Param("id") Integer id);
    @Update("update t_institutions set start = #{start} where id = #{id}")
    void updateInstStart(@Param("id") Integer id,@Param("start") Integer start);
}
