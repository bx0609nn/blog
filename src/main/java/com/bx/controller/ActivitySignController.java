package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.ActivitySign;
import com.bx.service.ActivitySignService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 活动报名
 */
@RestController
@RequestMapping("/activitySign")
public class ActivitySignController {

    @Resource
    ActivitySignService activitySignService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activitySign 活动报名
     * @return Result
     * @description 新增活动报名
     */
    @PostMapping("/add")
    public Result add(@RequestBody ActivitySign activitySign) {
        activitySignService.add(activitySign);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 活动报名ID
     * @return Result
     * @description 删除活动报名
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        activitySignService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 活动报名ID列表
     * @return Result
     * @description 批量删除活动报名
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        activitySignService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activitySign 活动报名
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询活动报名列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(ActivitySign activitySign,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ActivitySign> page = activitySignService.selectPage(activitySign, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activityId 活动ID
     * @param useId 用户ID
     * @return Result
     * @description 删除用户的活动报名
     */
    @DeleteMapping("/delete/user/{activityId}/{useId}")
    public Result userDelete(@PathVariable Long activityId, @PathVariable Long useId) {
        activitySignService.userDelete(activityId, useId);
        return Result.success();
    }

}