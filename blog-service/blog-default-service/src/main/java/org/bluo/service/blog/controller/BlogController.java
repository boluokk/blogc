package org.bluo.service.blog.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.service.blog.BlogClient;
import org.bluo.service.blog.config.BlogIPageImpl;
import org.bluo.service.blog.pojo.Blog;
import org.bluo.service.blog.service.impl.BlogServiceImpl;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取预览信息
     *
     * @param pageNun 页码
     * @param userId  用户ID
     * @return
     */
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

    /**
     * 添加博客
     *
     * @param blog 博客
     * @return
     */
    @PutMapping("/add")
    public Result add(@RequestBody Blog blog) {
        if (blogServiceImpl.save(blog)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 关键字查询
     *
     * @param key     关键字
     * @param pageNum 页码
     * @return
     */
    @GetMapping("search")
    public Result search(@RequestParam("key") String key,
                         @RequestParam("pageNum") int pageNum) {
        return Result.ok(blogServiceImpl.search(key, pageNum));
    }
}
