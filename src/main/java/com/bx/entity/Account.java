package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 角色用户父类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    
    // 用户id
    private Long id;
    
    // 用户名
    private String username;
    
    // 昵称
    private String name;
    
    // 密码
    private String password;
    
    // 角色标识
    private String role;
    
    // 新密码
    private String newPassword;
    
    // 头像
    private String avatar;
    
    // 盐值
    private String salt;
    
    // token
    private String token;

}
