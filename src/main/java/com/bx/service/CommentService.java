package com.bx.service;

import com.bx.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 评论Service
 */
public interface CommentService {

    /**
     * @param comment 评论信息
     * @return void
     * @description 新增评论
     */
    public void addComment(Comment comment);

    /**
     * @param id 评论ID
     * @return void
     * @description 删除评论
     */
    public void deleteById(Long id);

    /**
     * @param ids 评论ID列表
     * @return void
     * @description 批量删除评论
     */
    public void deleteBatch(List<Long> ids);

    /**
     * @param comment 评论信息
     * @return List<Comment>
     * @description 查询所有评论
     */
    public List<Comment> selectAll(Comment comment);

    /**
     * @param comment 评论信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Comment>
     * @description 查询评论列表
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize);

    /**
     * @param comment 评论信息
     * @return List<Comment>
     * @description 前台获取博客所有评论
     */
    public List<Comment> selectForUser(Comment comment);

    /**
     * @param id 评论ID
     * @return Comment
     * @description 根据ID查询评论详情
     */
    public Comment selectById(Long id);

    /**
     * @param comment 评论信息
     * @return void
     * @description 修改评论
     */
    public void updateById(Comment comment);

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return Long
     * @description 查询评论数量
     */
    public Long selectCount(Long fid, String module);

}
