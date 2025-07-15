package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Comment;
import com.bx.service.impl.CommentServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentServiceImpl commentService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param comment 评论
     * @return Result
     * @description 添加评论
     */
    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return Result.success();
    }
    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 评论ID
     * @return Result
     * @description 删除评论
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 评论ID列表
     * @return Result
     * @description 批量删除评论
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        commentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param comment 评论
     * @return Result
     * @description 查询所有评论
     */
    @GetMapping("/selectAll")
    public Result selectAll(Comment comment) {
        List<Comment> list = commentService.selectAll(comment);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param comment 评论
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询评论列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(Comment comment,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Comment> page = commentService.selectPage(comment, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 评论ID
     * @return Result
     * @description 根据ID查询评论详情
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Long id) {
        Comment comment = commentService.selectById(id);
        return Result.success(comment);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param comment 评论
     * @return Result
     * @description 查询用户评论
     */
    @GetMapping("/selectForUser")
    public Result selectForUser(Comment comment) {
        List<Comment> list = commentService.selectForUser(comment);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param comment 评论
     * @return Result
     * @description 更新评论
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Comment comment) {
        commentService.updateById(comment);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param fid 关联ID
     * @param module 模块
     * @return Result
     * @description 查询评论数量
     */
    @GetMapping("/selectCount")
    public Result selectCount(@RequestParam Long fid, @RequestParam String module) {
        Long count = commentService.selectCount(fid, module);
        return Result.success(count);
    }

}