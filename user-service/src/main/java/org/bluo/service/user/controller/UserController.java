package org.bluo.service.user.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.service.user.service.impl.UserServiceImpl;
import org.bluo.service.user.util.password.InvalidPassword;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author boluo
 * @date 2024/02/04
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * 修改密码
     *
     * @param userId  用户Id
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return 结果
     */
    @PostMapping("/modify/pass")
    public Result modifyPass(int userId, String oldPass, String newPass) {
        InvalidPassword password = userServiceImpl.updatePassword(userId, oldPass, newPass);
        if (ObjectUtil.isNull(password)) {
            return Result.ok("更新成功");
        }
        return Result.fail(password.getMessage());
    }
}
