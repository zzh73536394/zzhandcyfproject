package com.jk.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Video {

    private Integer id;

    private String videoName; //视频名称

    private Integer teacherId;//关联讲师ID

    private String videoTitle;//视频标题

    private String videoImg;//视频封面

    private Double videoPrice;//视频价格

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date videoTime;//视频发布时间

    private Integer videoStatus;//审核状态 0未审核 1 审核失败 2 审核成功

    private Integer quantity;//购买量

    private String videoinfo;//视频简介

    private String forPeople;//使用人群

    private String summary;//概述

    private String videourl;//视频路径

    private String kechengfenlei;//视频分类

    private String errorMsg;



    private String teacherName;//教师名称

    private String teacherInfo;//教师简介

    private String email;//教师邮箱




}
