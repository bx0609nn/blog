package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    // 评论id
    private Long id;
    
    // 博客id或活动id
    private  Long fid;
    
    // 评论内容
    private String content;
    
    // 用户id
    private Long userId;
    
    //一级评论id
    private Long rootId;
    
    // 回复评论id
    private Long pid;
    
    // 模块
    private String module;
    
    //评论时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
    // 用户名
    private String userName;
    
    // 用户头像
    private String avatar;
    
    // 子评论
    private List<Comment> children;

  
}
