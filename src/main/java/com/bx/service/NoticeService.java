package com.bx.service;

import com.bx.entity.Notice;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 公告Service
 */
public interface NoticeService {

    /**
     * @param notice 公告信息
     * @return void
     * @description 新增公告
     */
    public void add(Notice notice);

    /**
     * @param id 公告ID
     * @return void
     * @description 删除公告
     */
    public void deleteById(Integer id);

    /**
     * @param ids 公告ID列表
     * @return void
     * @description 批量删除公告
     */
    public void deleteBatch(List<Integer> ids);

    /**
     * @param notice 公告信息
     * @return List<Notice>
     * @description 查询所有公告
     */
    public List<Notice> selectAll(Notice notice);

    /**
     * @param notice   公告信息
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Notice>
     * @description 查询公告列表
     */
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize);

    /**
     * @param id 公告ID
     * @return Notice
     * @description 根据ID查询公告详情
     */
    public Notice selectById(Integer id);

    /**
     * @param notice 公告信息
     * @return void
     * @description 修改公告
     */
    public void updateById(Notice notice);

}
