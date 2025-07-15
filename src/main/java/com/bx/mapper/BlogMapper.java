package com.bx.mapper;

import com.bx.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 博客Mapper
 */
public interface BlogMapper {

    /**
     * 新增博客
     */
    int insert(Blog blog);

    /**
     * 删除博客
     */
    int deleteById(Long id);

    /**
     * 批量删除博客
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 查询所有博客
     */
    List<Blog> selectAll(Blog blog);

    /**
     * 根据ID查询博客详情
     */
    Blog selectById(Long id);

    /**
     * 修改博客
     */
    int updateById(Blog blog);

    /**
     * 查询用户发布的博客
     */
    @Select("select * from blog where user_id = #{userId}")
    List<Blog> selectUserBlog(Long userId);

    /**
     * 修改博客阅读数量
     */
    @Update("update blog set read_count = read_count + 1 where id = #{blogId}")
    void updateReadCount(Long blogId);

    /**
     * 查询用户点赞的博客
     */
    List<Blog> selectLike(Blog blog);

    /**
     * 查询用户收藏的博客
     */
    List<Blog> selectCollect(Blog blog);

    /**
     * 查询用户评论的博客
     */
    List<Blog> selectComment(Blog blog);

    /**
     * 根据阅读数排序
     */
    List<HashMap<Long,String>> selectByReadCount();

    /**
     * 根据分类ID查询博客
     */
    Set<Blog> selectByCategoryId(Integer categoryId, Long blogId);

}
