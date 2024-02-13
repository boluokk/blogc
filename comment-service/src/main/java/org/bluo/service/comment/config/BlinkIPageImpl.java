package org.bluo.service.comment.config;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.bluo.service.comment.pojo.vo.BlinkCommentVO;

/**
 * @author boluo
 * @date 2024/02/02
 */
public class BlinkIPageImpl extends Page<BlinkCommentVO> {

    /**
     * 每次查询评论个数
     */
    private static final int pageSize = 20;

    public BlinkIPageImpl() {
        super();
    }

    public BlinkIPageImpl(int pageNum) {
        super(pageNum, pageSize);
    }
}
