package org.blou.service.blink.controller;

import cn.hutool.core.util.ObjectUtil;
import org.blou.service.blink.service.impl.BlinkServiceImpl;
import org.bluo.common.util.respons.Result;
import org.bluo.service.blink.BlinkClient;
import org.bluo.service.blink.pojo.Blink;
import org.bluo.service.blink.pojo.PreViewInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 动态服务
 *
 * @author boluo
 * @date 2024/01/30
 */
@RestController
@RequestMapping("/blink")
public class BlinkController implements BlinkClient {
    @Resource
    private BlinkServiceImpl blinkServiceImpl;

    /**
     * 获取动态
     *
     * @param userId 用户Id
     * @return 动态信息
     */
    @Override
    @GetMapping("/get")
    public Result findBlink(int userId) {
        Blink blink = blinkServiceImpl.findBlinkByUserId(userId);
        if (ObjectUtil.isNotNull(blink)) {
            return Result.ok(blink);
        }
        return Result.fail();
    }

    /**
     * 获取动态预览信息
     *
     * @param blinkId 动态Id
     * @return 动态预览信息
     */
    @Override
    @GetMapping("/preview/info")
    public Result findPreviewInfo(int blinkId) {
        PreViewInfo preViewInfo = blinkServiceImpl.findPreViewInfoByBlinkId(blinkId);
        if (ObjectUtil.isNotNull(preViewInfo)) {
            return Result.ok(preViewInfo);
        }
        return Result.fail();
    }
}
