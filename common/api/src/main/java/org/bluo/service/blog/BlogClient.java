package org.bluo.service.blog;

import org.bluo.common.util.respons.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author boluo
 * @date 2024/01/30
 */
@FeignClient("blog-service")
public interface BlogClient {
    @GetMapping("/blog/get")
    Result findBlog(@RequestParam("blogId") int blogId);

    @GetMapping("/blog/preview")
    Result findPreviewInfo(@RequestParam("pageNum") int pageNun,
                           @RequestParam("userId") int userId);

}
