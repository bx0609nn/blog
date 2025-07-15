package com.bx.mapper;

import com.bx.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:49
 * @description 活动Mapper
 */
public interface ActivityMapper {

    /**
     * 新增活动
     */
    int insert(Activity activity);

    /**
     * 删除活动
     */
    int deleteById(Long id);

    /**
     * 批量删除活动
     */
    void deleteByIds(@Param("ids")List<Long> ids);

    /**
     * 查询所有活动
     */
    List<Activity> selectAll(Activity activity);

    /**
     * 根据ID查询活动详情
     */
    Activity selectById(Long id);

    /**
     * 修改活动
     */
    int updateById(Activity activity);

    /**
     * 查询用户报名的活动
     */
    List<Activity> selectUser(Activity activity);

    /**
     * 查询用户点赞的活动
     */
    List<Activity> selectLike(Activity activity);

    /**
     * 查询用户收藏的活动
     */
    List<Activity> selectCollect(Activity activity);

    /**
     * 查询用户评论的活动
     */
    List<Activity> selectComment(Activity activity);

    /**
     * 查询热门活动
     */
    List<HashMap<Long, String>> selectByReadCount();

    /**
     * 修改活动阅读数
     */
    @Update("update activity set read_count = read_count + 1 where id = #{activityId}")
    void updateReadCount(Long activityId);
}
