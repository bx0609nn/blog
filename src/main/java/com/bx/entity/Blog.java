package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 博客信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    // 博客主键
    private Long  id;
    
    // 博客标题
    private String title;
    
    // 博客简介
    private String descr;
    
    // 博客封面
    private String cover;
    
    // 博客内容
    private String content;
    
    // 博客标签
    private String tags;
    
    //发布人id
    private Long userId;
    
    // 浏览量
    private Integer readCount;
    
    // 分类id
    private Integer categoryId;
    
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
    // 分类名称
    private String categoryName;
    
    // 发布人名字
    private String userName;
    
    // 发布人
    private User user;
    
    // 点赞数
    private Integer likesCount;
    
    // 收藏数
    private Integer collectCount;
    
    // 是否被当前登录的用户点赞
    private Boolean userLike;  
    
    // 是否被当前登录的用户收藏
    private Boolean userCollect;
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blog)) return false;
        Blog blog = (Blog) o;
        return Objects.equals(getId(), blog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
