package com.bx.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.bx.common.Result;
import com.bx.common.enums.ResultCodeEnum;
import com.bx.common.enums.RoleEnum;
import com.bx.entity.Account;
import com.bx.service.impl.AdminServiceImpl;
import com.bx.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description
 */
@RestController
public class WebController {

    @Resource
    private AdminServiceImpl adminService;

    @Resource
    private UserServiceImpl userService;

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @return Result
     * @description 首页访问
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param account 账户
     * @return Result
     * @description 注册
     */
    @PostMapping("/sign")
    public Result sign(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.sign(account);
        } else {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.success();
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param account 账户
     * @return Result
     * @description 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        } else if (RoleEnum.USER.name().equals(account.getRole())) {
            account = userService.login(account);
        } else {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.success(account);
    }

    /**
     * @author lili
     * @date 2025/7/14 15:03
     * @param account 账户
     * @return Result
     * @description 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        } else if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.updatePassword(account);
        }
        return Result.success();
    }

}