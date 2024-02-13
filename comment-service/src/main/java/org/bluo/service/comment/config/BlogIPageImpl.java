package org.bluo.service.comment.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.bluo.service.comment.pojo.vo.BlogCommentVO;

/**
 * @author boluo
 * @date 2024/02/02
 */
public class BlogIPageImpl extends Page<BlogCommentVO> {
    /**
     * 每次查询评论个数
     */
    private static final int pageSize = 20;

    public BlogIPageImpl() {
        super();
    }

    public BlogIPageImpl(int pageNum) {
        super(pageNum, pageSize);
    }
}
