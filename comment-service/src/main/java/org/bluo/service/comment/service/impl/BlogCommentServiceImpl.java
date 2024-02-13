package org.bluo.service.comment.service.impl;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.service.comment.config.BlogIPageImpl;
import org.bluo.service.comment.config.SecondeCommentPageImpl;
import org.bluo.service.comment.mapper.BlogCommentMapper;
import org.bluo.service.comment.pojo.BlogComment;
import org.bluo.service.comment.pojo.vo.BlogCommentVO;
import org.bluo.service.comment.service.BlogCommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客评论 业务层
 *
 * @author boluo
 * @date 2024/01/30
 */

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Resource
    private BlogCommentMapper blogCommentMapper;

    @Override
    public BlogIPageImpl findPage(int pageNum, int blogId) {
        BlogIPageImpl blogIPage = new BlogIPageImpl(pageNum);
        // 1、先查出所有一级评论
        List<BlogCommentVO> parentComments =
                blogCommentMapper.selectPage(blogIPage, blogId);
        blogIPage.setRecords(parentComments);
        // 不存在评论，直接返回
        if (ObjectUtil.isEmpty(parentComments)) {
            return blogIPage;
        }
        List<Integer> parentIds = new ArrayList<>();
        Map<Integer, BlogCommentVO> hash = new HashMap<>();
        parentComments.forEach((comment) -> {
            parentIds.add(comment.getId());
            hash.put(comment.getId(), comment);
        });
        // 2、使用一级评论所有的Id查询查询二级部分评论
        List<BlogComment> childComments = blogCommentMapper.selectPartChild(parentIds);
        if (ObjectUtil.isEmpty(childComments)) {
            return blogIPage;
        }
        // 3、查询二级评论条数
        List<Integer> childCount = blogCommentMapper.selectChildCount(parentIds);
        // 4、放入二级评论
        for (BlogComment childComment : childComments) {
            BlogCommentVO parentComment = hash.get(childComment.getParentId());
            if (ObjectUtil.isEmpty(parentComment.getChildren())) {
                parentComment.setChildren(new ArrayList<>());
                parentComment.setTotal(childCount.removeFirst());
            }
            parentComment.getChildren().add(childComment);
        }
        return blogIPage;
    }

    @Override
    public SecondeCommentPageImpl findSecondPage(int pageNum, int parentId) {
        SecondeCommentPageImpl page = new SecondeCommentPageImpl(pageNum);
        page.setRecords(blogCommentMapper.selectByParentId(page, parentId));
        return page;
    }


}
