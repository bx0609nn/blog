package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Activity;
import com.bx.service.ActivityService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 活动
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @return Result
     * @description 新增活动
     */
    @ApiOperation
    @PostMapping("/add")
    public Result add(@RequestBody Activity activity) {
        activityService.add(activity);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 活动ID
     * @return Result
     * @description 删除活动
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long  id) {
        activityService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 活动ID列表
     * @return Result
     * @description 批量删除活动
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Long > ids) {
        activityService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @return Result
     * @description 查询所有活动
     */
    @GetMapping("/selectAll")
    public Result selectAll(Activity activity) {
        List<Activity> list = activityService.selectAll(activity);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询活动列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(Activity activity,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Activity> page = activityService.selectPage(activity, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 活动ID
     * @return Result
     * @description 根据ID查询活动详情
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Long  id) {
        Activity activity = activityService.selectById(id);
        return Result.success(activity);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @return Result
     * @description 查询热门活动
     */
    @GetMapping("/selectTop")
    public Result selectTop() {
        List<HashMap<Long,String>> list = activityService.selectTop();
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @return Result
     * @description 修改活动
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Activity activity) {
        activityService.updateById(activity);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activityId 活动ID
     * @return Result
     * @description 更新活动阅读数
     */
    @PutMapping("/updateReadCount/{activityId}")
    public Result updateReadCount(@PathVariable Long activityId) {
        activityService.updateReadCount(activityId);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 查询用户报名的活动
     */
    @GetMapping("/selectUser")
    public Result selectUser(Activity activity,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Activity> page = activityService.selectUser(activity, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 查询用户点赞的活动
     */
    @GetMapping("/selectLike")
    public Result selectLike(Activity activity,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Activity> page = activityService.selectLike(activity, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 查询用户收藏的活动
     */
    @GetMapping("/selectCollect")
    public Result selectCollect(Activity activity,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Activity> page = activityService.selectCollect(activity, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param activity 活动
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 查询用户评论的活动
     */
    @GetMapping("/selectComment")
    public Result selectComment(Activity activity,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Activity> page = activityService.selectComment(activity, pageNum, pageSize);
        return Result.success(page);
    }

}