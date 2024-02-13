package org.bluo.service.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.bluo.service.comment.config.BlinkIPageImpl;
import org.bluo.service.comment.mapper.BlinkCommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author boluo
 * @date 2024/02/02
 */

@SpringBootTest
public class TestCommentApp {

    @Resource
    BlinkCommentMapper blinkCommentMapper;

    @Test
    void test() {
        blinkCommentMapper.selectPage(new BlinkIPageImpl(1), 20);
    }
}
