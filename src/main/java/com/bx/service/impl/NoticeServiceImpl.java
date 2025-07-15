package com.bx.service.impl;

import cn.hutool.core.date.DateUtil;
import com.bx.entity.Account;
import com.bx.entity.Notice;
import com.bx.mapper.NoticeMapper;
import com.bx.service.NoticeService;
import com.bx.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 公告Service
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    /**
     * @param notice 公告信息
     * @return void
     * @description 新增公告
     */
    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        Account currentUser = TokenUtils.getCurrentUser();
        notice.setUser(currentUser.getUsername());
        noticeMapper.insert(notice);
    }

    /**
     * @param id 公告ID
     * @return void
     * @description 删除公告
     */
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    /**
     * @param ids 公告ID列表
     * @return void
     * @description 批量删除公告
     */
    public void deleteBatch(List<Integer> ids) {
        noticeMapper.deleteByIds(ids);
    }

    /**
     * @param notice 公告信息
     * @return List<Notice>
     * @description 查询所有公告
     */
    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    /**
     * @param notice   公告信息
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Notice>
     * @description 查询公告列表
     */
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = noticeMapper.selectAll(notice);
        return PageInfo.of(list);
    }

    /**
     * @param id 公告ID
     * @return Notice
     * @description 根据ID查询公告详情
     */
    public Notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    /**
     * @param notice 公告信息
     * @return void
     * @description 修改公告
     */
    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

}