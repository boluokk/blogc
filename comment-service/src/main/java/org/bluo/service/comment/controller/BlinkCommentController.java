package org.bluo.service.comment.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.service.comment.BlinkCommentClient;
import org.bluo.service.comment.pojo.vo.BlinkCommentVO;
import org.bluo.service.comment.service.impl.BlinkCommentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 动态评论
 *
 * @author boluo
 * @date 2024/01/30
 */

@RestController
@RequestMapping("/blink/comment")
public class BlinkCommentController implements BlinkCommentClient {

    @Resource
    private BlinkCommentServiceImpl blinkCommentServiceImpl;

    /**
     * 获取动态评论
     *
     * @param blinkId 动态评论Id
     * @return 动态评论
     */
    @GetMapping("/{pageNum}/{blinkId}")
    public Result findBlinkComment(@PathVariable("pageNum") int pageNum,
                                   @PathVariable("blinkId") int blinkId) {
        List<BlinkCommentVO> page = blinkCommentServiceImpl.findPage(pageNum, blinkId);
        if (ObjectUtil.isNotEmpty(page)) {
            return Result.ok(page);
        }
        return Result.fail();
    }
}
