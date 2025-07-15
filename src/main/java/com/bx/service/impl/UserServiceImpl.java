package com.bx.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.bx.common.Constants;
import com.bx.common.enums.ResultCodeEnum;
import com.bx.common.enums.RoleEnum;
import com.bx.entity.Account;
import com.bx.entity.User;
import com.bx.exception.CustomException;
import com.bx.mapper.UserMapper;
import com.bx.service.UserService;
import com.bx.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 用户Service
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * @param user 用户信息
     * @return void
     * @description 新增用户
     */
    public void add(User user) {
        // 业务方法
        // 1. 判断用户账号是否已经被注册
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser != null) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        // 2. 判断用户密码是不是空
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD); // 默认密码 123
        }
        // 3. 判断用户名称是不是空
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
        // 4. 默认用户角色
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }

    /**
     * @param account 账户信息
     * @return void
     * @description 注册
     */
    public void sign(Account account) {

        // 1. 判断用户名是否已经被注册
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (dbUser != null) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // 2. 用户名没有被注册则将密码使用盐值和Md5进行加密
        String salt = UUID.randomUUID().toString().toUpperCase();
        String password = getMd5Psw(salt, account.getPassword());
        account.setPassword(password);

        User user = new User();
        BeanUtils.copyProperties(account, user);
        user.setSalt(salt);

        // 3. 设置默认昵称
        user.setName("用户_"+RandomUtil.randomNumbers(8));

        // 4.设置默认头像
        user.setAvatar("http://localhost:9090/files/默认头像.png");

//        // 5. 设置用户默认为普通用户
//        user.setRole(RoleEnum.USER.name());

        // 4. 设置默认创建时间和更新时间
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        //5.保存用户到数据库
        int res = userMapper.insert(user);
        if (res == 0) {
            throw new CustomException(ResultCodeEnum.USER_REGISTER_ERROR);
        }

    }

    /**
     * @param account 账户信息
     * @return Account
     * @description 登录
     */
    public Account login(Account account) {

        // 1. 验证用户名是否正确
        Account dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 2. 验证密码是否正确
        String password = getMd5Psw( dbUser.getSalt(), account.getPassword());
        if (!password.equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // 3. 生成token
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getId().toString());

        // 4. 返回token
        dbUser.setToken(token);
        return dbUser;
    }

    /**
     * @param id 用户ID
     * @return void
     * @description 根据ID删除用户
     */
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    /**
     * @param ids 用户ID列表
     * @return void
     * @description 批量删除用户
     */
    public void deleteBatch(List<Long> ids) {
        userMapper.deleteByIds(ids);
    }

    /**
     * @param user 用户信息
     * @return List<User>
     * @description 查询所有用户
     */
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    /**
     * @param user 用户信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return PageInfo<User>
     * @description 查询用户列表
     */
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }

    /**
     * @param id 用户ID
     * @return User
     * @description 根据ID查询用户详情
     */
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * @param user 用户信息
     * @return void
     * @description 更新用户信息
     */
    public void updateUserInfo(User user) {
        log.info("user:{}", user);
        if (user.getBirth() != null) {
            user.setBirth(user.getBirth().plusDays(1));
        }
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    /**
     * @param account 账户信息
     * @return void
     * @description 修改密码
     */
    public void updatePassword(Account account) {
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 2. 验证密码是否正确
        String salt = dbUser.getSalt();
        String password = getMd5Psw( salt, account.getPassword());
        if (!password.equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // 3. 修改密码
        String newPassword = getMd5Psw(salt, account.getNewPassword());

        dbUser.setPassword(newPassword);
        this.updateUserInfo(dbUser);
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