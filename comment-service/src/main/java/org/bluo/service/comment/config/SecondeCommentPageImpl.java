package org.bluo.service.comment.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.bluo.service.comment.pojo.BlogComment;

/**
 * @author boluo
 * @date 2024/02/04
 */
public class SecondeCommentPageImpl extends Page<BlogComment> {
    /**
     * 每次查询评论个数
     */
    private static final int pageSize = 20;

    public SecondeCommentPageImpl() {
        super();
    }

    public SecondeCommentPageImpl(int pageNum) {
        super(pageNum, pageSize);
    }
}
