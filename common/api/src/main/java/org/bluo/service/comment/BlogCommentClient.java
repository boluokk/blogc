package org.bluo.service.comment;

import org.bluo.common.util.respons.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author boluo
 * @date 2024/01/30
 */
@FeignClient(value = "comment-service")
public interface BlogCommentClient {

    @GetMapping("/blog/comment/get")
    Result getBlogComment(@RequestParam("pageNum") int pageNum,
                          @RequestParam("blogId") int blogId);
    Result getSecondComment(@RequestParam("pageNum") int pageNum,
                            @RequestParam("parentId") int parentId);
}
