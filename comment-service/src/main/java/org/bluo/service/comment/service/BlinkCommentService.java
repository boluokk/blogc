package org.bluo.service.comment.service;

import org.bluo.service.comment.pojo.vo.BlinkCommentVO;

import java.util.List;

/**
 * @author boluo
 * @date 2024/01/30
 */
public interface BlinkCommentService {
    List<BlinkCommentVO> findPage(int pageNum, int blinkId);
}
