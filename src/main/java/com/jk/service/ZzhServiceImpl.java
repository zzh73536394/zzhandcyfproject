package com.jk.service;

import com.jk.bean.Ossbean;
import com.jk.bean.UserBean;
import com.jk.bean.kecheng;
import com.jk.dao.ZzhMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ZzhServiceImpl implements ZzhService {

    @Autowired
    private ZzhMapper zzhMapper;

    /*@Autowired
    private JedisPool jedisPool;*/

    @Override
    public HashMap<String, Object> findoss(Integer page, Integer limit) {
        Integer countsum = zzhMapper.countsum();
        Integer start = (page-1)*limit;
        List<Ossbean> findoss = zzhMapper.findoss(start, limit);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",countsum);
        map.put("data",findoss);
        map.put("code",0);
        return map;
    }

    @Override
    public void deleteBiaoti(String btid) {
        /*Jedis resource = jedisPool.getResource();
        resource.del("luobo");
        resource.close();*/
        zzhMapper.deleteBiaoti(btid);
    }

    @Override
    public void addLunbo(Ossbean ossbean) {
        /*Jedis resource = jedisPool.getResource();
        resource.del("luobo");
        resource.close();*/
        zzhMapper.addLunbo(ossbean);
    }



    @Override
    public UserBean findByName(String name) {
        // 查询用户是否存在
        UserBean bean = zzhMapper.findByName(name);

        if (bean != null) {
            // 查询用户信息、角色、权限
            bean = zzhMapper.findById(bean.getId());
        }
        return bean;
    }


    @Override
    public HashMap<String, Object> findyonghu(Integer page, Integer limit) {
        Integer countyonghu = zzhMapper.countyonghu();
        Integer start = (page-1)*limit;
        List<LinkedHashMap<String, Object>> findyonghu = zzhMapper.findyonghu(start,limit);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",countyonghu);
        map.put("data",findyonghu);
        map.put("code",0);
        return map;

    }

    @Override
    public void deleteyonghu(Integer id) {
        zzhMapper.deleteyonghu(id);
    }

    @Override
    public void updateLunbo(Ossbean ossbean) {
        /*Jedis resource = jedisPool.getResource();
        resource.del("luobo");
        resource.close();*/
        zzhMapper.updateLunbo(ossbean);
    }

    @Override
    public HashMap<String, Object> findkecheng(Integer page, Integer limit, kecheng kecheng) {
        Integer countkecheng = zzhMapper.countkecheng();
        Integer start = (page-1)*limit;
        List<LinkedHashMap<String, Object>> findkecheng = zzhMapper.findkecheng(start, limit, kecheng);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",countkecheng);
        map.put("data",findkecheng);
        map.put("code",0);
        return map;
    }


    @Override
    public void updatekechengfenlei(kecheng kecheng) {
        zzhMapper.updatekechengfenlei(kecheng);
    }
}
