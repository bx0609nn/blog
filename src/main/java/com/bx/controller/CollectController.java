package com.bx.controller;

import com.bx.common.Result;
import com.bx.entity.Collect;
import com.bx.service.CollectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lili
 * @version 1.0
 * @date 2025/7/15 9:49
 * @description 收藏
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    CollectService collectService;

    /**
     * @author lili
     * @date 2025/7/14 15:11
     * @param collect
     * @return Result
     * @description 设置收藏状态
     */
    @PostMapping("/set")
    public Result set(@RequestBody Collect collect) {
        int flag = collectService.set(collect);
        return Result.success(flag);
    }

}
