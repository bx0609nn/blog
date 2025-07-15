package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Notice;
import com.bx.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 公告
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param notice 公告
     * @return Result
     * @description 新增公告
     */
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        noticeService.add(notice);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 公告ID
     * @return Result
     * @description 删除公告
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 公告ID列表
     * @return Result
     * @description 批量删除公告
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        noticeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param notice 公告
     * @return Result
     * @description 查询所有公告
     */
    @GetMapping("/selectAll")
    public Result selectAll(Notice notice ) {
        List<Notice> list = noticeService.selectAll(notice);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param notice 公告
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询公告列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(Notice notice,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Notice> page = noticeService.selectPage(notice, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 公告ID
     * @return Result
     * @description 根据ID查询公告详情
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Notice notice = noticeService.selectById(id);
        return Result.success(notice);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param notice 公告
     * @return Result
     * @description 修改公告
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }

}