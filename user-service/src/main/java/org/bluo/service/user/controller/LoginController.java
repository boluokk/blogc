package org.bluo.service.user.controller;

import cn.hutool.core.util.ObjectUtil;
import global.pojo.User;
import global.pojo.dto.UserDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bluo.common.util.respons.Result;
import org.bluo.common.util.util.TokenUtil;
import org.bluo.service.user.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static global.pojo.convert.UserConvert.userConvert;

/**
 * 登录/注册
 *
 * @author boluo
 * @date 2024/01/29
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录Token
     */
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        User user = userServiceImpl.login(username, password);
        if (ObjectUtil.isNotNull(user)) {
            return Result.ok(TokenUtil.getToken(user));
        }
        return Result.fail("登录失败，账号不存在或者密码错误!");
    }

    /**
     * 注册
     *
     * @param userDTO 用户信息
     * @return 结果
     */
    @PostMapping("/register")
    public Result register(@RequestParam UserDTO userDTO) {
        // 查看是否已经注册
        if (ObjectUtil.isNotNull(userServiceImpl.findUserByUserName(userDTO.getUsername()))) {
            return Result.warn("用户已经注册");
        }
        if (userServiceImpl.register(userConvert.toUser(userDTO))) {
            log.info("用户注册: {}", userDTO);
            return Result.success();
        }
        return Result.fail("注册失败");
    }
}
