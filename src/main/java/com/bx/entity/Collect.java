package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * 收藏模块
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collect {
    
    //收藏id
    private Long  id;
    
    //博客活活动id
    private Long  fid;
    
    //用户id
    private Long userId;
    
    //模块
    private String module;
    
    //收藏时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
}
