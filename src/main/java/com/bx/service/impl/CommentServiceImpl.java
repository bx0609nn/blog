package com.bx.service.impl;

import com.bx.common.enums.RoleEnum;
import com.bx.entity.Account;
import com.bx.entity.Comment;
import com.bx.mapper.CommentMapper;
import com.bx.service.CommentService;
import com.bx.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 评论Service
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * @param comment 评论信息
     * @return void
     * @description 新增评论
     */
    public void addComment(Comment comment) {
        // 获取当前用户
        Account currentUser = TokenUtils.getCurrentUser();
        // 如果当前用户角色是USER
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            // 设置当前用户id
            comment.setUserId(currentUser.getId());
        }
        // 设置评论时间
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        // 先插入数据  拿到主键ID  再设置数据
        commentMapper.insert(comment);
        if (comment.getRootId() == null) {
            // 如果没有根评论
            comment.setRootId(comment.getId());
            comment.setCreateTime(LocalDateTime.now());
            comment.setUpdateTime(LocalDateTime.now());
            // 更新根评论id
            commentMapper.updateById(comment); // 注意 更新一下 root_id
        }

    }

    /**
     * @param id 评论ID
     * @return void
     * @description 删除评论
     */
    public void deleteById(Long id) {
        Comment comment = commentMapper.selectById(id);
        if (comment.getPid() == null){
            // 如果没有根评论，说明是根评论，不仅要删除评论，还要删除所有回复
            //即删除所有根节点rootId为id的评论
            List<Long> ids = commentMapper.selectAllByRootId(id);
            deleteBatch(ids);
        }
        commentMapper.deleteById(id);
    }

    /**
     * @param ids 评论ID列表
     * @return void
     * @description 批量删除评论
     */
    public void deleteBatch(List<Long> ids) {
        commentMapper.deleteByIds(ids);
    }

    /**
     * @param comment 评论信息
     * @return List<Comment>
     * @description 前台获取博客所有评论
     */
    public List<Comment> selectForUser(Comment comment) {
        List<Comment> commentList = commentMapper.selectForUser(comment);  // 查询一级的评论
        for (Comment c : commentList) {  // 查询回复列表
            Comment param = new Comment();
            param.setRootId(c.getId());
            List<Comment> children = this.selectAll(param);
            children = children.stream().filter(child -> !child.getId().equals(c.getId())).collect(Collectors.toList());  // 排除当前查询结果里最外层节点
            c.setChildren(children);
        }
        return commentList;
    }

    /**
     * @param comment 评论信息
     * @return List<Comment>
     * @description 查询所有评论
     */
    public List<Comment> selectAll(Comment comment) {
        return commentMapper.selectAll(comment);
    }

    /**
     * @param comment 评论信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Comment>
     * @description 查询评论列表
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> list = commentMapper.selectAll(comment);
        return PageInfo.of(list);
    }

    /**
     * @param id 评论ID
     * @return Comment
     * @description 根据ID查询评论详情
     */
    public Comment selectById(Long id) {
        return commentMapper.selectById(id);
    }

    /**
     * @param comment 评论信息
     * @return void
     * @description 修改评论
     */
    public void updateById(Comment comment) {
        comment.setUpdateTime(LocalDateTime.now());

        commentMapper.updateById(comment);
    }

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return Long
     * @description 查询评论数量
     */
    public Long selectCount(Long fid, String module) {
        return commentMapper.selectCount(fid, module);
    }

}