package com.bx.mapper;

import com.bx.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 公告Mapper
 */
public interface NoticeMapper {

    /**
     * 新增公告
     */
    int insert(Notice notice);

    /**
     * 删除公告
     */
    int deleteById(Integer id);

    /**
     * 批量删除公告
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 查询所有公告
     */
    List<Notice> selectAll(Notice notice);

    /**
     * 根据ID查询公告详情
     */
    Notice selectById(Integer id);

    /**
     * 修改公告
     */
    int updateById(Notice notice);

}
