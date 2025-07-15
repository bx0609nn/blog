package com.bx.service;

import com.bx.entity.Account;
import com.bx.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 用户Service
 */
public interface UserService {
    /**
     * @param user 用户信息
     * @return void
     * @description 新增用户
     */
    public void add(User user);

    /**
     * @param account 账户信息
     * @return void
     * @description 注册
     */
    public void sign(Account account);

    /**
     * @param account 账户信息
     * @return Account
     * @description 登录
     */
    public Account login(Account account);

    /**
     * @param id 用户ID
     * @return void
     * @description 根据ID删除用户
     */
    public void deleteById(Long id);

    /**
     * @param ids 用户ID列表
     * @return void
     * @description 批量删除用户
     */
    public void deleteBatch(List<Long> ids);

    /**
     * @param user 用户信息
     * @return List<User>
     * @description 查询所有用户
     */
    public List<User> selectAll(User user);

    /**
     * @param user 用户信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<User>
     * @description 查询用户列表
     */
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize);

    /**
     * @param id 用户ID
     * @return User
     * @description 根据ID查询用户详情
     */
    public User selectById(Long id);

    /**
     * @param user 用户信息
     * @return void
     * @description 更新用户信息
     */
    public void updateUserInfo(User user);

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
