package com.bx.service.impl;

import com.bx.common.enums.LikesModuleEnum;
import com.bx.common.enums.RoleEnum;
import com.bx.entity.*;
import com.bx.mapper.ActivityMapper;
import com.bx.service.ActivityService;
import com.bx.service.ActivitySignService;
import com.bx.service.CollectService;
import com.bx.service.LikesService;
import com.bx.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:49
 * @description 活动Service
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    ActivitySignService activitySignService;

    @Resource
    LikesService likesService;

    @Resource
    CollectService collectService;

    /**
     * @param activity 活动
     * @return void
     * @description 新增活动
     */
    public void add(Activity activity) {
        activityMapper.insert(activity);
    }

    /**
     * @param id 活动id
     * @return void
     * @description 删除活动
     */
    public void deleteById(Long id) {
        activityMapper.deleteById(id);
    }

    /**
     * @param ids 活动id列表
     * @return void
     * @description 批量删除活动
     */
    public void deleteBatch(List<Long> ids) {
        activityMapper.deleteByIds(ids);
    }


    /**
     * @param activity 活动查询条件
     * @return List<Activity> 活动列表
     * @description 查询所有活动
     */
    public List<Activity> selectAll(Activity activity) {
        return activityMapper.selectAll(activity);
    }

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 分页查询活动
     */
    public PageInfo<Activity> selectPage(Activity activity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectAll(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        Account currentUser = TokenUtils.getCurrentUser();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

    /**
     * @param id 活动id
     * @return Activity 活动信息
     * @description 根据ID查询活动详情
     */
    public Activity selectById(Long id) {
        Activity activity = activityMapper.selectById(id);
        this.setAct(activity, TokenUtils.getCurrentUser());

        int likesCount = likesService.selectByFidAndModule(id, LikesModuleEnum.ACTIVITY.getValue());
        int collectCount = collectService.selectByFidAndModule(id, LikesModuleEnum.ACTIVITY.getValue());
        activity.setLikesCount(likesCount);
        activity.setCollectCount(collectCount);

        Likes likes = likesService.selectUserLikes(id, LikesModuleEnum.ACTIVITY.getValue());
        activity.setIsLike(likes != null);

        Collect collect = collectService.selectUserCollect(id, LikesModuleEnum.ACTIVITY.getValue());
        activity.setIsCollect(collect != null);
        return activity;
    }

    /**
     * @param activity 活动
     * @return void
     * @description 修改活动
     */
    public void updateById(Activity activity) {
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.updateById(activity);
    }

    /**
     * @param act 活动
     * @param currentUser 当前用户
     * @return void
     * @description 设置活动额外信息
     */
    private void setAct(Activity act, Account currentUser) {
        if (act.getEndTime().isBefore(LocalDateTime.now()));// 活动的结束时间在当前时间之前  就表示活动结束了

        ActivitySign activitySign = activitySignService.selectByActivityIdAndUserId(act.getId(), currentUser.getId());
        act.setIsSign(activitySign != null);
    }

    /**
     * @return List<HashMap<Long,String>> 热门活动列表
     * @description 查询热门活动
     */
    public List<HashMap<Long,String>> selectTop() {
        List<HashMap<Long,String>> activityList = activityMapper.selectByReadCount();
        return activityList;
    }

    /**
     * @param activityId 活动id
     * @return void
     * @description 更新活动阅读量
     */
    public void updateReadCount(Long activityId) {
        activityMapper.updateReadCount(activityId);
    }

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户报名的活动列表
     */
    public PageInfo<Activity> selectUser(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectUser(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户点赞的活动
     */
    public PageInfo<Activity> selectLike(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectLike(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户收藏的活动
     */
    public PageInfo<Activity> selectCollect(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectCollect(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

    /**
     * @param activity 活动查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return PageInfo<Activity> 分页活动信息
     * @description 查询用户评论的活动
     */
    public PageInfo<Activity> selectComment(Activity activity, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            activity.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Activity> list = activityMapper.selectComment(activity);
        PageInfo<Activity> pageInfo = PageInfo.of(list);
        List<Activity> activityList = pageInfo.getList();
        for (Activity act : activityList) {
            this.setAct(act, currentUser);
        }
        return pageInfo;
    }

}