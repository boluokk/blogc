package org.bluo.service.esblog;

import global.pojo.BlogDoc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */
@FeignClient("blog-elasticsearch")
public interface ESBlogClient {
    @GetMapping("/search")
    public List<BlogDoc> getPage(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize);
}
