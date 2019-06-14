package com.jk.managementsystem.dao;

import com.jk.managementsystem.bean.MenuTree;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;


public interface ManageMapper {
    @Select("Select * from t_tree")
    List<LinkedHashMap<String, Object>> getTree();
    @Select("select * from t_tree")
    List<MenuTree> getTreeAll();
}
