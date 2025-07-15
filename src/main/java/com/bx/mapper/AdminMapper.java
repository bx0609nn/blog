package com.bx.mapper;

import com.bx.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 管理员Mapper
 */
public interface AdminMapper {

    /**
     * 新增管理员
     */
    int insert(Admin admin);

    /**
     * 删除管理员
     */
    int deleteById(Long id);

    /**
     * 批量删除管理员
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 查询所有管理员
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 根据ID查询管理员详情
     */
    Admin selectById(Long id);

    /**
     * 修改管理员
     */
    int updateById(Admin admin);

    /**
     * 根据用户名查询管理员
     */
    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);

}
