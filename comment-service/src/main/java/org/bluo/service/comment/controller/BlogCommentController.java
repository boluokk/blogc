package org.bluo.service.comment.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.service.comment.BlogCommentClient;
import org.bluo.service.comment.config.BlogIPageImpl;
import org.bluo.service.comment.config.SecondeCommentPageImpl;
import org.bluo.service.comment.service.impl.BlogCommentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客评论
 *
 * @author boluo
 * @date 2024/01/30
 */

@RestController
@RequestMapping("/blog/comment")
public class BlogCommentController implements BlogCommentClient {
    @Resource
    private BlogCommentServiceImpl blogCommentServiceImpl;

    /**
     * 获取博客评论
     *
     * @param pageNum 页码
     * @param blogId  博客评论Id
     * @return 博客评论
     */
    @GetMapping("/get")
    public Result getBlogComment(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("blogId") int blogId) {
        BlogIPageImpl page = blogCommentServiceImpl.findPage(pageNum, blogId);
        if (ObjectUtil.isNotNull(page)) {
            return Result.ok(page);
        }
        return Result.fail();
    }

    /**
     * 查询二级评论
     *
     * @param pageNum  页码
     * @param parentId 父评论Id
     * @return 评论集合
     */
    @Override
    @GetMapping("/second")
    public Result getSecondComment(@RequestParam("pageNum") int pageNum,
                                   @RequestParam("parentId") int parentId) {
        SecondeCommentPageImpl page = blogCommentServiceImpl.findSecondPage(pageNum, parentId);
        if (ObjectUtil.isNotNull(page)) {
            return Result.ok(page);
        }
        return Result.fail();
    }
}
