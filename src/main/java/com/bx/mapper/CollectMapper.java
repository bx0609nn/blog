package com.bx.mapper;

import com.bx.entity.Collect;
import org.apache.ibatis.annotations.Param;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 收藏Mapper
 */
public interface CollectMapper {

    /**
     * 新增收藏
     */
    void insert(Collect collect);

    /**
     * 删除收藏
     */
    void deleteById(Long id);

    /**
     * 根据用户查询收藏
     */
    Collect selectUserCollect(Collect collect);

    /**
     * 根据关联ID和模块查询收藏
     */
    int selectByFidAndModule(@Param("fid") Long fid, @Param("module") String module);
}
