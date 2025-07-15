package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 管理员
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    // 管理员id
    private Long id;
    
    // 管理员用户名
    private String username;
    
    // 管理员密码
    private String password;
    
    // 管理员昵称
    private String name;
    
    // 管理员电话
    private String phone;
    
    // 管理员邮箱
    private String email;
    
    // 管理员头像
    private String avatar;
    
    // 管理员角色
    private String role;
    
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
   
}
