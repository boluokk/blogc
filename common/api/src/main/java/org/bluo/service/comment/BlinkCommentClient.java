package org.bluo.service.comment;

import org.bluo.common.util.respons.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author boluo
 * @date 2024/01/30
 */

@FeignClient(value = "comment-service")
public interface BlinkCommentClient {
    Result findBlinkComment(@PathVariable("pageNum") int pageNum,
                            @PathVariable("blinkId") int blinkId);
}
