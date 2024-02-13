package org.bluo.service.blog.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 博客 分页设置
 *
 * @author boluo
 * @date 2024/02/03
 */
public class BlogIPageImpl extends Page {
    private static int pageSize = 20;

    public BlogIPageImpl(long current) {
        super(current, pageSize);
    }
}
