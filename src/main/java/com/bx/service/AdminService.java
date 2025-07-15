package com.bx.service;

import com.bx.entity.Account;
import com.bx.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 管理员Service
 */
public interface AdminService {

    /**
     * @param admin 管理员
     * @return void
     * @description 新增管理员
     */
    public void add(Admin admin);

    /**
     * @param account 账户信息
     * @return Account
     * @description 登录
     */
    public Account login(Account account);

    /**
     * @param id 管理员ID
     * @return void
     * @description 删除管理员
     */
    public void deleteById(Long id);

    /**
     * @param ids 管理员ID列表
     * @return void
     * @description 批量删除管理员
     */
    public void deleteBatch(List<Long> ids);

    /**
     * @param admin 管理员信息
     * @return List<Admin>
     * @description 查询所有管理员
     */
    public List<Admin> selectAll(Admin admin);

    /**
     * @param admin 管理员信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<Admin>
     * @description 查询管理员列表
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize);

    /**
     * @param id 管理员ID
     * @return Admin
     * @description 根据ID查询管理员详情
     */
    public Admin selectById(Long id);

    /**
     * @param admin 管理员信息
     * @return void
     * @description 修改管理员
     */
    public void updateById(Admin admin);

    /**
     * @param account 账户信息
     * @return void
     * @description 修改密码
     */
    public void updatePassword(Account account);

    /**
     * @param salt 盐值
     * @param password 明文密码
     * @return String
     * @description 将明文密码进行加密
     */
    public String getMd5Psw(String salt, String password);

}
