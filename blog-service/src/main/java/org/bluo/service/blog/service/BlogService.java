package org.bluo.service.blog.service;

import org.bluo.service.blog.config.BlogIPageImpl;
import org.bluo.service.blog.pojo.Blog;
import org.bluo.service.blog.pojo.vo.BlogPreviewVO;

import java.util.List;

/**
 * @author boluo
 * @date 2024/01/30
 */
public interface BlogService {
    BlogIPageImpl getPreviewPage(int pageNum, int userId);

    Blog findOne(int userId);
}
