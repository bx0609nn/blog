package com.bx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公告信息表
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    
    /** 标题 */
    private String title;
    
    /** 内容 */
    private String content;
    
    /** 创建时间 */
    private String time;
    
    /** 创建人 */
    private String user;

}
