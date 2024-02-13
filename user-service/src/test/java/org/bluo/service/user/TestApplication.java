package org.bluo.service.user;

import org.bluo.service.user.pojo.convert.PersonalInfoConvert;
import org.bluo.service.user.service.impl.LikesServiceImpl;
import org.bluo.service.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * @author boluo
 * @date 2024/01/27
 */
@SpringBootTest
public class TestApplication {
    @Resource
    UserServiceImpl userServiceImpl;

    @Resource
    PersonalInfoConvert personalInfoConvert;

    @Resource
    LikesServiceImpl likesServiceImpl;

    @Test
    void test() {
        System.out.println(likesServiceImpl.findAllBlogsByUserId(2));
    }
}
