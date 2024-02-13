package org.bluo.service.user.service;

import java.util.List;

/**
 *
 * @author boluo
 * @date 2024/01/29
 */
public interface LikesService {
    List<Integer> findAllBlogsByUserId(int userId);
}
