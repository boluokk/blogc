package org.bluo.service.comment.service.impl;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.service.comment.config.BlinkIPageImpl;
import org.bluo.service.comment.mapper.BlinkCommentMapper;
import org.bluo.service.comment.pojo.BlinkComment;
import org.bluo.service.comment.pojo.vo.BlinkCommentVO;
import org.bluo.service.comment.service.BlinkCommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态评论 业务层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Service
public class BlinkCommentServiceImpl implements BlinkCommentService {

    @Resource
    private BlinkCommentMapper blinkCommentMapper;

    public List<BlinkCommentVO> findPage(int pageNum, int blinkId) {

        // 1、先查出所有一级评论
        List<BlinkCommentVO> parentComments = blinkCommentMapper.selectPage(new BlinkIPageImpl(pageNum), blinkId);
        // 不存在评论，直接返回
        if (ObjectUtil.isEmpty(parentComments)) {
            return parentComments;
        }
        List<Integer> parentIds = new ArrayList<>();
        parentComments.forEach((comment) -> {
            parentIds.add(comment.getId());
        });
        // 2、使用一级评论所有的Id查询查询二级部分评论
        List<BlinkComment> childComments = blinkCommentMapper.selectPartChild(parentIds);
        if (ObjectUtil.isEmpty(childComments)) {
            return parentComments;
        }
        // 3、查询二级评论条数
        List<Integer> childCount = blinkCommentMapper.selectChildCount(parentIds);
        // O^2：待优化
        int curIdx = 0;
        for (BlinkCommentVO parentComment : parentComments) {
            int parentId = parentComment.getId();
            for (BlinkComment childComment : childComments) {
                if (ObjectUtil.isNotNull(parentComment.getChildren()) && parentId == childComment.getParentId()) {
                    parentComment.getChildren().add(childComment);
                } else {
                    List<BlinkComment> newChild = new ArrayList<>();
                    newChild.add(childComment);
                    parentComment.setTotal(childCount.get(curIdx++));
                    parentComment.setChildren(newChild);
                }
            }
        }

        return parentComments;
    }

}
