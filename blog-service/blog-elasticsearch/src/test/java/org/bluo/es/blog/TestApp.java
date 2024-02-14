package org.bluo.es.blog;

import jakarta.annotation.Resource;
import org.bluo.es.blog.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */
@SpringBootTest
public class TestApp {

    @Resource
    BlogMapper blogMapper;

    @Test
    void test() {
    }
}
