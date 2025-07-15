package com.bx.service.impl;

import com.bx.entity.Account;
import com.bx.entity.Likes;
import com.bx.mapper.LikesMapper;
import com.bx.service.LikesService;
import com.bx.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/14 15:47
 * @description 点赞Service
 */
@Service
public class LikesServiceImpl implements LikesService {

    @Resource
    LikesMapper likesMapper;

    /**
     * @param likes 点赞信息
     * @return int
     * @description 点赞和取消点赞
     */
    public int set(Likes likes) {
        //获取当前用户
        Account currentUser = TokenUtils.getCurrentUser();
        //得到当前用户的id
        likes.setUserId(currentUser.getId());
        //查询用户是否点过赞
        Likes dblLikes = likesMapper.selectUserLikes(likes);
        //如果没有则是点赞
        if (dblLikes == null) {
            //点赞
            likes.setCreateTime(LocalDateTime.now());
            likes.setUpdateTime(LocalDateTime.now());
            likesMapper.insert(likes);
            return 1;
        } else {//有则是取消点赞
            //取消点赞
            likesMapper.deleteById(dblLikes.getId());
            return 0;
        }
    }

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return Likes
     * @description 查询当前用户是否点过赞
     */
    public Likes selectUserLikes(Long fid, String module) {
        Account currentUser = TokenUtils.getCurrentUser();
        Likes likes = new Likes();
        likes.setUserId(currentUser.getId());
        likes.setFid(fid);
        likes.setModule(module);
        return likesMapper.selectUserLikes(likes);
    }

    /**
     * @param fid 关联ID
     * @param module 模块名称
     * @return int
     * @description 根据关联ID和模块查询点赞数量
     */
    public int selectByFidAndModule(Long fid, String module) {
        return likesMapper.selectByFidAndModule(fid, module);
    }

}