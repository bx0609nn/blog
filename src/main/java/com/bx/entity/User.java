package com.bx.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 用户
 *
 * @author BX
 * @date 2024/01/5 10:04:97
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account {
    // 用户id
    private Long id;
    
    // 用户名
    private String username;
    
    // 用户密码
    private String password;
    
    // 用户昵称
    private String name;
    
    // 用户头像
    private String avatar;
    
    // 用户id
    private String role;
    
    // 用户性别
    private String sex;
    
    // 用户手机号
    private String phone;
    
    // 用户邮箱
    private String email;
    
    // 用户简介
    private String info;
    
    // 用户生日
    private LocalDate birth;
    
    // 用户盐值
    private String salt;
    
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 用户发布的博客数
    private Integer blogCount;
    
    // 用户点赞数
    private Integer likesCount;
    
    // 用户收藏数
    private Integer collectCount;

}
