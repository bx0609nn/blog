package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.User;
import com.bx.service.impl.UserServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param user 用户
     * @return Result
     * @description 用户注册
     */
    @PostMapping("/sign")
    public Result add(@RequestBody User user) {
        userService.sign(user);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 用户ID
     * @return Result
     * @description 删除用户
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param ids 用户ID列表
     * @return Result
     * @description 批量删除用户
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {  //  [1,2,3]
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param user 用户
     * @return Result
     * @description 查询所有用户
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param user 用户
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result
     * @description 分页查询用户列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param id 用户ID
     * @return Result
     * @description 根据ID查询用户详情
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Long id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param user 用户
     * @return Result
     * @description 修改用户
     */
    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody User user) {
        userService.updateUserInfo(user);
        return Result.success();
    }
}