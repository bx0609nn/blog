package com.bx.service.impl;

import com.bx.entity.Account;
import com.bx.entity.Collect;
import com.bx.mapper.CollectMapper;
import com.bx.service.CollectService;
import com.bx.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:48
 * @description 收藏Service
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    CollectMapper collectMapper;

    /**
     * @param collect 收藏信息
     * @return int
     * @description 设置收藏状态（收藏/取消收藏）
     */
    public int set(Collect collect) {
        Account currentUser = TokenUtils.getCurrentUser();
        collect.setUserId(currentUser.getId());
        Collect dblCollect = collectMapper.selectUserCollect(collect);
        if (dblCollect == null) {
            collect.setCreateTime(LocalDateTime.now());
            collect.setUpdateTime(LocalDateTime.now());
            collectMapper.insert(collect);
            return 1;
        } else {
            collectMapper.deleteById(dblCollect.getId());
            return 0;
        }
    }

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return Collect
     * @description 查询用户ID和fid和模块查询收藏
     */
    public Collect selectUserCollect(Long  fid, String module) {
        Account currentUser = TokenUtils.getCurrentUser();
        Collect collect = new Collect();
        collect.setUserId(currentUser.getId());
        collect.setFid(fid);
        collect.setModule(module);
        return collectMapper.selectUserCollect(collect);
    }

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return int
     * @description 根据关联ID和模块查询收藏数量
     */
    public int selectByFidAndModule(Long  fid, String module) {
        return collectMapper.selectByFidAndModule(fid, module);
    }

}