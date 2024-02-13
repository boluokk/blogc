package org.bluo.service.user.service.impl;

import org.bluo.service.user.mapper.LikesMapper;
import org.bluo.service.user.service.LikesService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 用户收藏 业务层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Service
public class LikesServiceImpl implements LikesService {

    @Resource
    private LikesMapper likesMapper;

    @Override
    public List<Integer> findAllBlogsByUserId(int userId) {
        return likesMapper.getBlogIdByUserId(userId);
    }
}
