package com.bx.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.bx.common.Constants;
import com.bx.common.enums.ResultCodeEnum;
import com.bx.common.enums.RoleEnum;
import com.bx.entity.Account;
import com.bx.entity.Admin;
import com.bx.exception.CustomException;
import com.bx.mapper.AdminMapper;
import com.bx.service.AdminService;
import com.bx.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 管理员Service
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * @param admin 管理员
     * @return void
     * @description 新增管理员
     */
    public void add(Admin admin) {
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }


        // 1. 生成盐
        String salt = UUID.randomUUID().toString().toUpperCase();
        String password = getMd5Psw(salt, admin.getPassword());
        admin.setSalt(salt);
        admin.setPassword(password);


        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole(RoleEnum.ADMIN.name());
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insert(admin);
    }

    /**
     * @param account 账户信息
     * @return Account
     * @description 登录
     */
    public Account login(Account account) {
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 2. 验证密码是否正确
        String password = getMd5Psw( dbAdmin.getSalt(), account.getPassword());
        if (!password.equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // 生成token
        String tokenData = dbAdmin.getId() + "-" + RoleEnum.ADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbAdmin.getId().toString());
        dbAdmin.setToken(token);
        return dbAdmin;
    }

    /**
     * @param id 管理员ID
     * @return void
     * @description 删除管理员
     */
    public void deleteById(Long id) {
        adminMapper.deleteById(id);
    }

    /**
     * @param ids 管理员ID列表
     * @return void
     * @description 批量删除管理员
     */
    public void deleteBatch(List<Long> ids) {
        adminMapper.deleteByIds(ids);
    }

    /**
     * @param admin 管理员信息
     * @return List<Admin>
     * @description 查询所有管理员
     */
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * @param admin 管理员信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Admin>
     * @description 查询管理员列表
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    /**
     * @param id 管理员ID
     * @return Admin
     * @description 根据ID查询管理员详情
     */
    public Admin selectById(Long id) {
        return adminMapper.selectById(id);
    }

    /**
     * @param admin 管理员信息
     * @return void
     * @description 修改管理员
     */
    public void updateById(Admin admin) {
        adminMapper.updateById(admin);
    }

    /**
     * @param account 账户信息
     * @return void
     * @description 修改密码
     */
    public void updatePassword(Account account) {
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 2. 验证密码是否正确
        String salt = dbAdmin.getSalt();
        String password = getMd5Psw( salt, account.getPassword());
        if (!password.equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }


        // 3. 修改密码
        String newPassword = getMd5Psw(salt, account.getNewPassword());
        dbAdmin.setPassword(newPassword);
        adminMapper.updateById(dbAdmin);
    }

    /**
     * @param salt 盐值
     * @param password 明文密码
     * @return String
     * @description 将明文密码进行加密
     */
    public String getMd5Psw(String salt, String password) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}