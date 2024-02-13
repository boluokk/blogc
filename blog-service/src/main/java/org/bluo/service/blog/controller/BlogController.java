package org.bluo.service.blog.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.service.blog.BlogClient;
import org.bluo.service.blog.config.BlogIPageImpl;
import org.bluo.service.blog.pojo.Blog;
import org.bluo.service.blog.service.impl.BlogServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客
 *
 * @author boluo
 * @date 2024/01/30
 */
@RestController
@RequestMapping("/blog")
public class BlogController implements BlogClient {

    @Resource
    private BlogServiceImpl blogServiceImpl;

    @Override
    @GetMapping("/get")
    public Result findBlog(@RequestParam("blogId") int blogId) {
        Blog blog = blogServiceImpl.findOne(blogId);
        if (ObjectUtil.isNotNull(blog)) {
            return Result.ok(blog);
        }
        return Result.fail();
    }

    @Override
    @GetMapping("/preview")
    public Result findPreviewInfo(@RequestParam("pageNum") int pageNun,
                                  @RequestParam("userId") int userId) {
        BlogIPageImpl page = blogServiceImpl.getPreviewPage(pageNun, userId);
        if (ObjectUtil.isNotNull(page)) {
            return Result.ok(page);
        }
        return Result.fail();
    }
}
