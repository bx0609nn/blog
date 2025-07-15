package com.bx.service;

import com.bx.entity.Collect;
import java.time.LocalDateTime;
import com.bx.entity.Account;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 收藏Service
 */
public interface CollectService {
    /**
     * @param collect 收藏信息
     * @return int
     * @description 设置收藏状态（收藏/取消收藏）
     */
    public int set(Collect collect);

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return Collect
     * @description 查询用户ID和fid和模块查询收藏
     */
    Collect selectUserCollect(Long  fid, String module);

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return int
     * @description 根据关联ID和模块查询收藏数量
     */
    int selectByFidAndModule(Long  fid, String module);
}
