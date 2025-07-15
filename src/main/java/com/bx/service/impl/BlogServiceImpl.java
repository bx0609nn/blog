package com.bx.service.impl;

import com.bx.common.enums.LikesModuleEnum;
import com.bx.common.enums.RoleEnum;
import com.bx.entity.*;
import com.bx.mapper.BlogMapper;
import com.bx.service.BlogService;
import com.bx.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 博客Service
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    
    @Resource
    private BlogMapper blogMapper;
    
    @Resource
    UserServiceImpl userService;
    
    @Resource
    LikesServiceImpl likesService;
    
    @Resource
    CollectServiceImpl collectService;

    /**
     * @param blog 博客
     * @return void
     * @description 新增博客
     */
    public void add(Blog blog) {
        
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            blog.setUserId(currentUser.getId());
            blog.setCreateTime(LocalDateTime.now());
            blog.setUpdateTime(LocalDateTime.now());
        }
        blogMapper.insert(blog);
    }

    /**
     * @param id id
     * @return void
     * @description 删除博客
     */
    public void deleteById(Long id) {
        blogMapper.deleteById(id);
    }

    /**
     * @param ids ID集合
     * @return void
     * @description 批量删除博客
     */
    public void deleteBatch(List<Long> ids) {
        blogMapper.deleteByIds(ids);
    }

    /**
     * @param blog 博客
     * @return void
     * @description 查询所有博客
     */
    public List<Blog> selectAll(Blog blog) {
        
        return blogMapper.selectAll(blog);
    }

    /**
     * @param blog 博客
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo
     * @description 查询博客列表
     */
    public PageInfo<Blog> selectPage(Blog blog, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> list = blogMapper.selectAll(blog);
        for (Blog b : list) {
            int likesCount = likesService.selectByFidAndModule(b.getId(), LikesModuleEnum.BLOG.getValue());
            b.setLikesCount(likesCount);
        }
        return PageInfo.of(list);
    }

    /**
     * @param id id
     * @return Blog 博客
     * @description 根据ID查询博客详情
     */
    public Blog selectById(Long id){
        // 1、查询博客信息
        Blog blog = blogMapper.selectById(id);

        // 1.1、查询该博客的点赞数和当前用户是否点赞过
        int likesCount = likesService.selectByFidAndModule(id, LikesModuleEnum.BLOG.getValue());
        blog.setLikesCount(likesCount);

        Likes userLikes = likesService.selectUserLikes(id, LikesModuleEnum.BLOG.getValue());
        blog.setUserLike(userLikes != null);

        // 1.2、展示该博客的收藏数和当前用户是否点赞过
        int collectCount = collectService.selectByFidAndModule(id, LikesModuleEnum.BLOG.getValue());
        blog.setCollectCount(collectCount);

        Collect userCollect = collectService.selectUserCollect(id, LikesModuleEnum.BLOG.getValue());
        blog.setUserCollect(userCollect != null);

        // 2、根据博客中的作者id查询作者信息
        User user = userService.selectById(blog.getUserId());

        // 2.1、查询作者发布的博客数
        List<Blog> userBlogList = blogMapper.selectUserBlog(user.getId());
        user.setBlogCount(userBlogList.size());

        // 2.2、博客作者收到的点赞和收藏数
        int userLikesCount = 0;
        int userCollectCount = 0;
        // 遍历作者的每篇博客，统计作者所有博客的点赞数和收藏数
        for (Blog b : userBlogList) {
            Long fid = b.getId();
            likesCount = likesService.selectByFidAndModule(fid, LikesModuleEnum.BLOG.getValue());
            userLikesCount += likesCount;

            collectCount = collectService.selectByFidAndModule(fid, LikesModuleEnum.BLOG.getValue());
            userCollectCount += collectCount;
        }
        // 设置作者的点赞和收藏数
        user.setLikesCount(userLikesCount);
        user.setCollectCount(userCollectCount);

        // 设置作者信息
        blog.setUser(user);

        return blog;
    }

    /**
     * @param blog 博客
     * @return void
     * @description 修改博客
     */
    public void updateBlog(Blog blog) {
        blog.setUpdateTime(LocalDateTime.now());
        blogMapper.updateById(blog);
    }

    /**
     * @param blogId id
     * @return Set
     * @description 查询相关分类博客
     */
    public Set<Blog> selectRecommend(Long blogId){
        Blog blog = this.selectById(blogId);
        Integer categoryId = blog.getCategoryId();
        if (categoryId == null) {
            return Collections.emptySet();
        }
        Set<Blog> blogSet = blogMapper.selectByCategoryId(blog.getCategoryId(),blogId);

        blogSet.forEach(b -> {
            int likesCount = likesService.selectByFidAndModule(b.getId(), LikesModuleEnum.BLOG.getValue());
            b.setLikesCount(likesCount);
        });
        return blogSet;
    }

    /**
     * @param
     * @return List
     * @description 查询热门博客
     */
    public List<HashMap<Long,String>> selectTop() {
        List<HashMap<Long,String>> blogList = blogMapper.selectByReadCount();
        return blogList;
    }

    /**
     * @param blogId id
     * @return void
     * @description 修改博客阅读数量
     */
    public void updateReadCount(Long blogId) {
        blogMapper.updateReadCount(blogId);
    }
    
    public PageInfo<Blog> selectUser(Blog blog, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            blog.setUserId(currentUser.getId());
        }
        return this.selectPage(blog, pageNum, pageSize);
    }

    /**
     * @param blog 博客
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo
     * @description 查询用户点赞的博客
     */
    public PageInfo<Blog> selectLike(Blog blog, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            blog.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> list = blogMapper.selectLike(blog);
        PageInfo<Blog> pageInfo = PageInfo.of(list);
        List<Blog> blogList = pageInfo.getList();
        for (Blog b : blogList) {
            int likesCount = likesService.selectByFidAndModule(b.getId(), LikesModuleEnum.BLOG.getValue());
            b.setLikesCount(likesCount);
        }
        return pageInfo;
    }

    /**
     * @param blog 博客
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo
     * @description 查询用户收藏的博客
     */
    public PageInfo<Blog> selectCollect(Blog blog, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            blog.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> list = blogMapper.selectCollect(blog);
        PageInfo<Blog> pageInfo = PageInfo.of(list);
        List<Blog> blogList = pageInfo.getList();
        for (Blog b : blogList) {
            int likesCount = likesService.selectByFidAndModule(b.getId(), LikesModuleEnum.BLOG.getValue());
            b.setLikesCount(likesCount);
        }
        return pageInfo;
    }

    /**
     * @param blog 博客
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo
     * @description 查询用户评论的博客
     */
    public PageInfo<Blog> selectComment(Blog blog, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            blog.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> list = blogMapper.selectComment(blog);
        PageInfo<Blog> pageInfo = PageInfo.of(list);
        List<Blog> blogList = pageInfo.getList();
        for (Blog b : blogList) {
            int likesCount = likesService.selectByFidAndModule(b.getId(), LikesModuleEnum.BLOG.getValue());
            b.setLikesCount(likesCount);
        }
        return pageInfo;
    }
}
