package org.blou.service.blink.service.impl;

import org.blou.service.blink.mapper.BlinkMapper;
import org.blou.service.blink.mapper.PreViewInfoMapper;
import org.blou.service.blink.service.BlinkService;
import org.bluo.service.blink.pojo.Blink;
import org.bluo.service.blink.pojo.PreViewInfo;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 动态 业务层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Service
public class BlinkServiceImpl implements BlinkService {

    @Resource
    private BlinkMapper blinkMapper;
    @Resource
    private PreViewInfoMapper preViewInfoMapper;

    @Override
    public Blink findBlinkByUserId(int userId) {
        return blinkMapper.findByUserId(userId);
    }

    @Override
    public PreViewInfo findPreViewInfoByBlinkId(int blinkId) {
        return preViewInfoMapper.findByBlinkId(blinkId);
    }
}
