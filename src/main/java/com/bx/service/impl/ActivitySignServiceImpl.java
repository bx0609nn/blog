package com.bx.service.impl;

import com.bx.common.enums.ResultCodeEnum;
import com.bx.entity.Account;
import com.bx.entity.ActivitySign;
import com.bx.exception.CustomException;
import com.bx.mapper.ActivitySignMapper;
import com.bx.service.ActivitySignService;
import com.bx.utils.TokenUtils;
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
 * @description 活动报名Service
 */
@Service
public class ActivitySignServiceImpl implements ActivitySignService {

    @Resource
    ActivitySignMapper activitySignMapper;

    /**
     * @param activitySign 活动报名信息
     * @return void
     * @description 添加活动报名信息
     */
    public void add(ActivitySign activitySign) {
        // 获取当前用户
        Account currentUser = TokenUtils.getCurrentUser();
        // 查看用户是否已经报名
        ActivitySign as = this.selectByActivityIdAndUserId(activitySign.getActivityId(), currentUser.getId());
        if (as != null) {
            // 如果已经报名，则抛出异常
            throw new CustomException(ResultCodeEnum.ACTIVITY_SIGN_ERROR);
        }
        // 设置用户ID、创建时间和更新时间
        activitySign.setUserId(currentUser.getId());
        activitySign.setCreateTime(LocalDateTime.now());
        activitySign.setUpdateTime(LocalDateTime.now());
        // 插入报名信息
        activitySignMapper.insert(activitySign);
    }

    /**
     * @param id 活动报名信息ID
     * @return void
     * @description 删除活动报名信息
     */
    public void deleteById(Long id) {
        activitySignMapper.deleteById(id);
    }

    /**
     * @param ids 活动报名信息ID列表
     * @return void
     * @description 批量删除活动报名信息
     */
    public void deleteBatch(List<Long> ids) {
        activitySignMapper.deleteByIds(ids);
    }

    /**
     * @param activitySign 活动报名信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<ActivitySign>
     * @description 查询所有活动报名信息
     */
    public PageInfo<ActivitySign> selectPage(ActivitySign activitySign, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ActivitySign> list = activitySignMapper.selectAll(activitySign);
        return PageInfo.of(list);
    }

    /**
     * @param actId 活动ID
     * @param userId 用户ID
     * @return ActivitySign
     * @description 根据活动ID和用户ID查询活动报名信息
     */
    public ActivitySign selectByActivityIdAndUserId(Long actId, Long userId) {
        return activitySignMapper.selectByActivityIdAndUserId(actId, userId);
    }

    /**
     * @param activityId 活动ID
     * @param useId 用户ID
     * @return void
     * @description 根据活动ID和用户ID删除活动报名信息
     */
    public void userDelete(Long activityId, Long useId) {
        activitySignMapper.userDelete(activityId, useId);
    }
}