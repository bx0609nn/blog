package com.bx.service;

import com.bx.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 博客分类Service
 */
public interface CategoryService {
    /**
     * @param category 博客分类
     * @return void
     * @description 新增博客分类
     */
    public void add(Category category);

    /**
     * @param id 博客分类ID
     * @return void
     * @description 删除博客分类
     */
    public void deleteById(Integer id);

    /**
     * @param ids 博客分类ID列表
     * @return void
     * @description 批量删除博客分类
     */
    public void deleteBatch(List<Integer> ids);

    /**
     * @param category 博客分类信息
     * @return List<Category>
     * @description 查询所有博客分类
     */
    public List<Category> selectAll(Category category);

    /**
     * @param category 博客分类信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Category>
     * @description 查询博客分类列表
     */
    public PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize);

    /**
     * @param id 博客分类ID
     * @return Category
     * @description 根据ID查询博客分类详情
     */
    public Category selectById(Integer id);

    /**
     * @param category 博客分类信息
     * @return void
     * @description 修改博客分类
     */
    public void updateById(Category category);

}
