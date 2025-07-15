package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * 点赞模块
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    
    // 点赞id
    private Long  id;
    
    // 博客id或活动id
    private Long  fid;
    
    // 点赞用户id
    private Long userId;
    
    //模块
    private String module;
    
    // 点赞时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
   
}
