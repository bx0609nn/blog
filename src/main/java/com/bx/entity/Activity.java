package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    // 活动id
    private Long id;
    
    // 活动名称
    private String name;
    
    // 活动描述
    private String descr;
    
    // 活动封面
    private String cover;
    
    // 活动内容
    private String content;
    
    // 活动开始时间
    private LocalDateTime startTime;
    
    // 活动结束时间
    private LocalDateTime endTime;
    
    // 活动形式
    private String form;
    
    // 活动地点
    private String address;
    
    // 活动主办方
    private String host;
    
    // 浏览量
    private Integer readCount;
    
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
    // 活动是否结束
    private Boolean isEnd;
    
    // 用户id
    private Long userId;
    
    // 活动是否报名
    private Boolean isSign;  
    
    // 活动点赞数
    private Integer likesCount;
    
    // 活动收藏数
    private Integer collectCount;

    // 用户是否点赞
    private Boolean isLike;
    
    // 用户是否收藏
    private Boolean isCollect;

}
