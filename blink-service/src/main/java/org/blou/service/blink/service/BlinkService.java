package org.blou.service.blink.service;

import org.bluo.service.blink.pojo.Blink;
import org.bluo.service.blink.pojo.PreViewInfo;

/**
 * @author boluo
 * @date 2024/01/30
 */
public interface BlinkService {
    Blink findBlinkByUserId(int userId);

    PreViewInfo findPreViewInfoByBlinkId(int blinkId);
}
