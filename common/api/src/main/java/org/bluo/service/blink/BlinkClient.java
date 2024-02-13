package org.bluo.service.blink;

import org.bluo.common.util.respons.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author boluo
 * @date 2024/01/30
 */
@FeignClient(value = "blink-service")
public interface BlinkClient {
    @GetMapping("/blink/get")
    Result findBlink(int userId);

    @GetMapping("/blink/preview/info")
    Result findPreviewInfo(int blinkId);
}
