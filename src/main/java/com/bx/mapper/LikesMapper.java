package com.bx.mapper;

import com.bx.entity.Likes;
import org.apache.ibatis.annotations.Param;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 点赞Mapper
 */
public interface LikesMapper {

    /**
     * 新增点赞
     */
    void insert(Likes likes);

    /**
     * 删除点赞
     */
    void deleteById(Long id);

    /**
     * 根据用户查询点赞
     */
    Likes selectUserLikes(Likes likes);

    /**
     * 根据关联ID和模块查询点赞数量
     */
    int selectByFidAndModule(@Param("fid") Long fid, @Param("module") String module);
}
