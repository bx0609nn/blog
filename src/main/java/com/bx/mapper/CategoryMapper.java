package com.bx.mapper;

import com.bx.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 博客分类Mapper
 */
public interface CategoryMapper {

    /**
     * 新增博客分类
     */
    int insert(Category category);

    /**
     * 删除博客分类
     */
    int deleteById(Integer id);
    /**
     * 批量删除博客分类
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 查询所有博客分类
     */
    List<Category> selectAll(Category category);

    /**
     * 根据ID查询博客分类详情
     */
    Category selectById(Integer id);

    /**
     * 修改博客分类
     */
    int updateById(Category category);

}
