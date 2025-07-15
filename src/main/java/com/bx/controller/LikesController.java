package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Likes;
import com.bx.service.LikesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 点赞
 */
@RestController
@RequestMapping("/likes")
public class LikesController {

    @Resource
    LikesService likesService;

    /**
     * @author lili
     * @date 2025/7/14 15:11
     * @param likes
     * @return Result
     * @description 设置点赞状态
     */
    @PostMapping("/set")
    public Result set(@RequestBody Likes likes) {
        int flag = likesService.set(likes);
        return Result.success(flag);
    }

}
