package org.bluo.service.user.controller;

import cn.hutool.core.util.ObjectUtil;
import org.bluo.common.util.respons.Result;
import org.bluo.service.user.pojo.vo.PersonalInfoVO;
import org.bluo.service.user.service.impl.PersonalInfoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 用户信息
 *
 * @author boluo
 * @date 2024/01/29
 */

@RestController
@RequestMapping("/user/personal")
public class PersonalInfoController {
    @Resource
    private PersonalInfoServiceImpl personalInfoServiceImpl;

    /**
     * 获取用户信息
     *
     * @param userId 用户Id
     * @return 用户信息
     */
    @GetMapping("/info")
    public Result findPersonalInfo(@RequestParam("userId") int userId) {
        PersonalInfoVO personalInfo = personalInfoServiceImpl.findPersonalInfo(userId);
        if (ObjectUtil.isNotNull(personalInfo)) {
            return Result.ok(personalInfo);
        }
        return Result.fail();
    }
}
