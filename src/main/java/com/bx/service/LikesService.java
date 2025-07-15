package com.bx.service;

import com.bx.entity.Likes;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 点赞Service
 */
public interface LikesService {
    /**
     * @param likes 点赞信息
     * @return int
     * @description 点赞和取消点赞
     */
    public int set(Likes likes);

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return Likes
     * @description 查询当前用户是否点过赞
     */
    public Likes selectUserLikes(Long fid, String module);

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return int
     * @description 根据关联ID和模块查询点赞数量
     */
    public int selectByFidAndModule(Long fid, String module);

}
