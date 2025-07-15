package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Category;
import com.bx.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 博客分类
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param category 博客分类
     * @return Result
     * @description 新增博客分类
     */
    @PostMapping("/add")
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 分类ID
     * @return Result
     * @description 删除博客分类
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 分类ID列表
     * @return Result
     * @description 批量删除博客分类
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        categoryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param category 博客分类
     * @return Result
     * @description 查询所有博客分类
     */
    @GetMapping("/selectAll")
    public Result selectAll(Category category) {
        List<Category> list = categoryService.selectAll(category);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param category 博客分类
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询博客分类列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(Category category,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Category> page = categoryService.selectPage(category, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 分类ID
     * @return Result
     * @description 根据ID查询博客分类详情
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Category category = categoryService.selectById(id);
        return Result.success(category);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param category 博客分类
     * @return Result
     * @description 修改博客分类
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.success();
    }

}