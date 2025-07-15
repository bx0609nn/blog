package com.bx.mapper;

import com.bx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 用户Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户
     */
    int insert(User user);

    /**
     * 删除用户
     */
    void deleteById(Long id);

    /**
     * 批量删除用户
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 查询所有用户
     */
    List<User> selectAll(User user);

    /**
     * 根据ID查询用户详情
     */
    User selectById(Long id);

    /**
     * 根据用户名查询用户
     */
    User selectByUsername(String username);

    /**
     * 修改用户
     */
    void updateById(User user);

}
