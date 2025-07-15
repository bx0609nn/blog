package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Blog;
import com.bx.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 博客
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @return Result
     * @description 新增博客
     */
    @PostMapping("/add")
    public Result add(@RequestBody Blog blog) {
        blogService.add(blog);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 博客ID
     * @return Result
     * @description 删除博客
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long  id) {
        blogService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 博客ID列表
     * @return Result
     * @description 批量删除博客
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Long > ids) {
        blogService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @return Result
     * @description 查询所有博客
     */
    @GetMapping("/selectAll")
    public Result selectAll(Blog blog) {
        List<Blog> list = blogService.selectAll(blog);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询博客列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(Blog blog,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Blog> page = blogService.selectPage(blog, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 博客ID
     * @return Result
     * @description 根据ID查询博客详情
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Long  id) {
        Blog blog = blogService.selectById(id);
        return Result.success(blog);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @return Result
     * @description 修改博客
     */
    @PutMapping("/update")
    public Result updateBlog(@RequestBody Blog blog) {
        blogService.updateBlog(blog);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blogId 博客ID
     * @return Result
     * @description 更新博客阅读数
     */
    @PutMapping("/updateReadCount/{blogId}")
    public Result updateReadCount(@PathVariable Long  blogId) {
        blogService.updateReadCount(blogId);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @return Result
     * @description 查询博客榜单
     */
    @GetMapping("/selectTop")
    public Result selectTop() {
        List<HashMap<Long,String>> list = blogService.selectTop();
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blogId 博客ID
     * @return Result
     * @description 查询博客推荐
     */
    @GetMapping("/selectRecommend/{blogId}")
    public Result selectRecommend(@PathVariable Long  blogId) {
        Set<Blog> blogSet = blogService.selectRecommend(blogId);
        return Result.success(blogSet);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询当前用户的博客列表
     */
    @GetMapping("/selectUser")
    public Result selectUser(Blog blog,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Blog> page = blogService.selectUser(blog, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 查询当前用户点赞的博客列表
     */
    @GetMapping("/selectLike")
    public Result selectLike(Blog blog,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Blog> page = blogService.selectLike(blog, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 查询当前用户收藏的博客列表
     */
    @GetMapping("/selectCollect")
    public Result selectCollect(Blog blog,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Blog> page = blogService.selectCollect(blog, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 查询当前用户评论的博客列表
     */
    @GetMapping("/selectComment")
    public Result selectComment(Blog blog,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Blog> page = blogService.selectComment(blog, pageNum, pageSize);
        return Result.success(page);
    }

}