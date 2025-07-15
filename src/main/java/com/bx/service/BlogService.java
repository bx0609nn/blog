package com.bx.service;

import com.bx.entity.Blog;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 博客Service
 */
public interface BlogService {

    /**
     * @param blog 博客
     * @return void
     * @description 新增博客
     */
    public void add(Blog blog);

    /**
     * @param id id
     * @return void
     * @description 删除博客
     */
    public void deleteById(Long id);

    /**
     * @param ids ID集合
     * @return void
     * @description 批量删除博客
     */
    public void deleteBatch(List<Long> ids);

    /**
     * @param blog 博客
     * @return void
     * @description 查询所有博客
     */
    public List<Blog> selectAll(Blog blog);

    /**
     * @param blog,pageNum,pageSize 博客，页码，页面大小
     * @return PageInfo
     * @description 分页查询博客列表
     */
    public PageInfo<Blog> selectPage(Blog blog, Integer pageNum, Integer pageSize);

    /**
     * @param id id
     * @return Blog 博客
     * @description 根据ID查询博客详情
     */
    public Blog selectById(Long id);

    /**
     * @param blogId id
     * @return Set
     * @description 查询相关分类博客
     */
    public Set<Blog> selectRecommend(Long blogId);

    /**
     * @param
     * @return List
     * @description 查询热门博客
     */
    public List<HashMap<Long,String>> selectTop();

    /**
     * @param blog 博客
     * @return void
     * @description 修改博客
     */
    public void updateBlog(Blog blog);

    /**
     * @param blogId id
     * @return void
     * @description 修改博客阅读数量
     */
    public void updateReadCount(Long blogId);

    /**
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return PageInfo
     * @description 查询用户点赞的博客
     */
    public PageInfo<Blog> selectLike(Blog blog, Integer pageNum, Integer pageSize);

    /**
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return PageInfo
     * @description 查询用户收藏的博客
     */
    public PageInfo<Blog> selectCollect(Blog blog, Integer pageNum, Integer pageSize);

    /**
     * @param blog 博客
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return PageInfo
     * @description 查询用户评论的博客
     */
    public PageInfo<Blog> selectComment(Blog blog, Integer pageNum, Integer pageSize);

}
