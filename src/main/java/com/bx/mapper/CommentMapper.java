package com.bx.mapper;

import com.bx.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 评论Mapper
 */
public interface CommentMapper {

    /**
     * 新增评论
     */
    int insert(Comment comment);

    /**
     * 删除评论
     */
    int deleteById(Long id);

    /**
     * 批量删除评论
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 查询所有评论
     */
    List<Comment> selectAll(Comment comment);

    /**
     * 根据用户查询评论
     */
    List<Comment> selectForUser(Comment comment);

    /**
     * 根据ID查询评论详情
     */
    Comment selectById(Long id);

    /**
     * 修改评论
     */
    int updateById(Comment comment);

    /**
     * 查询评论数量
     */
    @Select("select count(*) from comment where fid = #{fid} and module = #{module}")
    Long selectCount(@Param("fid") Long fid, @Param("module") String module);

    /**
     * 根据rootId查询评论
     */
    List<Long> selectAllByRootId(Long id);

}
