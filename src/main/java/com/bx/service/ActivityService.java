package com.bx.service;

import com.bx.entity.Activity;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:49
 * @description 活动Service
 */
public interface ActivityService {

    /**
     * @param activity 活动
     * @return void
     * @description 新增活动
     */
    public void add(Activity activity);

    /**
     * @param id 活动id
     * @return void
     * @description 删除活动
     */
    public void deleteById(Long id);

    /**
     * @param ids 活动id列表
     * @return void
     * @description 批量删除活动
     */
    public void deleteBatch(List<Long> ids);

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 分页查询活动
     */
    public PageInfo<Activity> selectPage(Activity activity, Integer pageNum, Integer pageSize);

    /**
     * @param id 活动id
     * @return Activity 活动信息
     * @description 根据ID查询活动详情
     */
    public Activity selectById(Long id);

    /**
     * @return List<HashMap<Long,String>> 热门活动列表
     * @description 查询热门活动
     */

    public List<HashMap<Long,String>> selectTop();
    /**
     * @param activity 活动
     * @return void
     * @description 修改活动
     */
    public void updateById(Activity activity);

    /**
     * @param activity 活动查询条件
     * @return List<Activity> 活动列表
     * @description 查询所有活动
     */
    public List<Activity> selectAll(Activity activity);

    /**
     * @param activityId 活动id
     * @return void
     * @description 更新活动阅读量
     */
    public void updateReadCount(Long activityId);

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户报名的活动列表
     */
    public PageInfo<Activity> selectUser(Activity activity, Integer pageNum, Integer pageSize);

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户点赞的活动
     */
    public PageInfo<Activity> selectLike(Activity activity, Integer pageNum, Integer pageSize);

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户收藏的活动
     */
    public PageInfo<Activity> selectCollect(Activity activity, Integer pageNum, Integer pageSize);

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户评论的活动
     */
    public PageInfo<Activity> selectComment(Activity activity, Integer pageNum, Integer pageSize);

}
