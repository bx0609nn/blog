package com.bx.service;

import com.bx.entity.ActivitySign;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 活动报名Service
 */
public interface ActivitySignService {

    /**
     * @param activitySign 活动报名信息
     * @return void
     * @description 添加活动报名信息
     */
    public void add(ActivitySign activitySign);

    /**
     * @param id 活动报名信息ID
     * @return void
     * @description 删除活动报名信息
     */
    public void deleteById(Long id);

    /**
     * @param ids 活动报名信息ID列表
     * @return void
     * @description 批量删除活动报名信息
     */
    public void deleteBatch(List<Long> ids);

    /**
     * @param activitySign 活动报名信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<ActivitySign>
     * @description 查询所有活动报名信息
     */
    public PageInfo<ActivitySign> selectPage(ActivitySign activitySign, Integer pageNum, Integer pageSize);


    /**
     * @param activityId 活动ID
     * @param useId 用户ID
     * @return void
     * @description 根据活动ID和用户ID删除活动报名信息
     */
    public void userDelete(Long activityId, Long useId);

    /**
     * @param actId 活动ID
     * @param userId 用户ID
     * @return ActivitySign
     * @description 根据活动ID和用户ID查询活动报名信息
     */
    public ActivitySign selectByActivityIdAndUserId(Long actId, Long userId);
}
