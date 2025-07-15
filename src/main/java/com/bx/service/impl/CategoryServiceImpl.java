package com.bx.service.impl;

import com.bx.entity.Category;
import com.bx.mapper.CategoryMapper;
import com.bx.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 博客分类Service
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * @param category 博客分类
     * @return void
     * @description 新增博客分类
     */
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insert(category);
    }

    /**
     * @param id 博客分类ID
     * @return void
     * @description 删除博客分类
     */
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

    /**
     * @param ids 博客分类ID列表
     * @return void
     * @description 批量删除博客分类
     */
    public void deleteBatch(List<Integer> ids) {
        categoryMapper.deleteByIds(ids);
    }

    /**
     * @param category 博客分类信息
     * @return List<Category>
     * @description 查询所有博客分类
     */
    public List<Category> selectAll(Category category) {
        return categoryMapper.selectAll(category);
    }

    /**
     * @param category 博客分类信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Category>
     * @description 查询博客分类列表
     */
    public PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> list = categoryMapper.selectAll(category);
        return PageInfo.of(list);
    }

    /**
     * @param id 博客分类ID
     * @return Category
     * @description 根据ID查询博客分类详情
     */
    public Category selectById(Integer id) {
        return categoryMapper.selectById(id);
    }

    /**
     * @param category 博客分类信息
     * @return void
     * @description 修改博客分类
     */
    public void updateById(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateById(category);
    }

}