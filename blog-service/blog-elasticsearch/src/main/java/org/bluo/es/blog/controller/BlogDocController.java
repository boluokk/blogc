package org.bluo.es.blog.controller;

import global.pojo.BlogDoc;
import jakarta.annotation.Resource;
import org.bluo.es.blog.service.impl.BlogDocServiceImpl;
import org.bluo.service.esblog.ESBlogClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/13
 */

@RestController
@RequestMapping("/blog/doc")
public class BlogDocController implements ESBlogClient {


    @Resource
    private BlogDocServiceImpl blogDocServiceImpl;

    /**
     * 查询博客标题或者描述
     *
     * @param title       标题
     * @param description 描述
     * @param pageNum     页码
     * @param pageSize    页大小
     * @return
     */
    @Override
    @GetMapping("/search")
    public List<BlogDoc> getPage(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize) {
        return blogDocServiceImpl.getPage(title, description, pageNum, pageSize);
    }
}
