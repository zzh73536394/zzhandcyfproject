package com.jk.managementsystem.service;



import com.jk.managementsystem.bean.MenuTree;

import java.util.LinkedHashMap;
import java.util.List;

public interface ManageService {
    List<LinkedHashMap<String, Object>> getTree();

    List<MenuTree> getTreeAll();
}
