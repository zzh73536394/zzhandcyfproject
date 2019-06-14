package com.jk.managementsystem.service;


import com.jk.managementsystem.bean.MenuTree;
import com.jk.managementsystem.dao.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
