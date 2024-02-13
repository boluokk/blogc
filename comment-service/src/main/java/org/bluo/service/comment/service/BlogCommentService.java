package org.bluo.service.comment.service;

import org.bluo.service.comment.config.BlogIPageImpl;
import org.bluo.service.comment.config.SecondeCommentPageImpl;
import org.bluo.service.comment.pojo.BlogComment;
import org.bluo.service.comment.pojo.vo.BlogCommentVO;

import java.util.List;

/**
 * @author boluo
 * @date 2024/01/30
 */
public interface BlogCommentService {
    BlogIPageImpl findPage(int pageNum, int blinkId);

    SecondeCommentPageImpl findSecondPage(int pageNum, int parentId);
}
