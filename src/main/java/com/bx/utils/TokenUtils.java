package com.bx.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bx.common.Constants;
import com.bx.common.enums.RoleEnum;
import com.bx.entity.Account;
import com.bx.service.impl.AdminServiceImpl;
import com.bx.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Token工具类
 */
@Component
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    private static AdminServiceImpl staticAdminService;
    private static UserServiceImpl staticUserService;

    @Resource
    AdminServiceImpl adminService;

    @Resource
    UserServiceImpl userService;

    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
        staticUserService = userService;
    }

    /**
     * 生成token
     */
    public static String createToken(String data, String sign) {
        return JWT.create().withAudience(data) // 将 userId-role 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 id 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     */
    public static Account getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader(Constants.TOKEN);
            if (ObjectUtil.isNotEmpty(token)) {
                String userRole = JWT.decode(token).getAudience().get(0);
                String userId = userRole.split("-")[0];  // 获取用户id
                String role = userRole.split("-")[1];    // 获取角色
                if (RoleEnum.ADMIN.name().equals(role)) {
                    return staticAdminService.selectById(Long.valueOf(userId));
                } else if (RoleEnum.USER.name().equals(role)) {
                    return staticUserService.selectById(Long.valueOf(userId));
                }
            }
        } catch (Exception e) {
            log.error("获取当前用户信息出错", e);
        }
        return new Account();  // 返回空的账号对象
    }
}

