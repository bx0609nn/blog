package com.bx.mapper;

import com.bx.entity.ActivitySign;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 活动报名Mapper
 */
public interface ActivitySignMapper {

    /**
     * 添加活动报名信息
     */
    @Insert("insert into activity_sign ( activity_id, user_id, create_time,update_time ) values ( #{activityId}, #{userId}, #{createTime},#{updateTime})")
    void insert(ActivitySign activitySign);

    /**
     * 删除活动报名信息
     */
    @Delete("delete from activity_sign where id = #{id}")
    void deleteById(Long id);

    /**
     * 批量删除活动报名信息
     */
    @Delete("<script>delete from activity_sign where id in <foreach collection='ids' item='id' open='(' close=')' separator=','>#{id}</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 查询所有活动报名信息
     */
    @Select("select activity_sign.*, activity.name as activityName, user.name as userName from activity_sign " +
            "left join activity on activity_sign.activity_id = activity.id " +
            "left join user on activity_sign.user_id = user.id " +
            "order by id desc")
    List<ActivitySign> selectAll(ActivitySign activitySign);

    /**
     * 根据活动ID和用户ID查询活动报名信息
     */
    @Select("select * from activity_sign where activity_id = #{activityId} and user_id = #{userId}")
    ActivitySign selectByActivityIdAndUserId(@Param("activityId") Long actId, @Param("userId") Long userId);

    /**
     * 根据活动ID和用户ID删除活动报名信息
     */
    @Delete("delete from activity_sign where activity_id = #{activityId} and user_id = #{userId}")
    void userDelete(@Param("activityId") Long activityId, @Param("userId") Long useId);

}
