package com.jk.dao;

import com.jk.bean.Ossbean;
import com.jk.bean.UserBean;
import com.jk.bean.kecheng;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface ZzhMapper{

    @Select("select count(*) from t_oss")
    Integer countsum();

    @Select("select * from t_oss limit #{start},#{limit}")
    List<Ossbean> findoss(@Param("start") Integer start, @Param("limit") Integer limit);

    @Delete("delete from t_oss where id =#{btid}")
    void deleteBiaoti(String btid);

    void addLunbo(Ossbean ossbean);

    @Select("select id,name,`password` from `user` where name= #{username}")
    UserBean findByName(String username);

    UserBean findById(Integer id);

    @Select("select * from user limit #{start},#{limit}")
    List<LinkedHashMap<String,Object>> findyonghu(@Param("start") Integer start, @Param("limit") Integer limit);

    @Delete("delete from user where id =#{id}")
    void deleteyonghu(Integer id);

    @Select("select count(*) from user")
    Integer countyonghu();

    @Update("update t_oss set href=#{href},hrefname=#{hrefname} where id=#{id}")
    void updateLunbo(Ossbean ossbean);

    @Select("select count(*) from t_video")
    Integer countkecheng();

    List<LinkedHashMap<String,Object>> findkecheng(@Param("start") Integer start,@Param("limit") Integer limit,@Param("kecheng") kecheng kecheng);

    @Insert("insert into t_video(kechengfenlei) values(#{kechengfenlei})")
    void updatekechengfenlei(kecheng kecheng);
}
