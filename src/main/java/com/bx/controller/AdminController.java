package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Admin;
import com.bx.service.impl.AdminServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 管理员
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminServiceImpl adminService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param admin 管理员
     * @return Result
     * @description 新增管理员
     */
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 管理员ID
     * @return Result
     * @description 删除管理员
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 管理员ID列表
     * @return Result
     * @description 批量删除管理员
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        adminService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param admin 管理员
     * @return Result
     * @description 查询所有管理员
     */
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        List<Admin> list = adminService.selectAll(admin);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param admin 管理员
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询管理员
     */
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Admin> page = adminService.selectPage(admin, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 管理员ID
     * @return Result
     * @description 根据ID查询管理员详情
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Long id) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param admin 管理员
     * @return Result
     * @description 修改管理员
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.success();
    }

}