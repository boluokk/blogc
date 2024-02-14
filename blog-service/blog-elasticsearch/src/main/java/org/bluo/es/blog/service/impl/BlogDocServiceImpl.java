package org.bluo.es.blog.service.impl;

import global.pojo.BlogDoc;
import jakarta.annotation.Resource;
import org.bluo.es.blog.mapper.BlogMapper;
import org.bluo.es.blog.service.BlogDocService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */
@Service
public class BlogDocServiceImpl implements BlogDocService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public List<BlogDoc> getPage(String title, String description, int pageNum, int pageSize) {
        Page<BlogDoc> page = blogMapper.findByTitleOrDescription(title, description, PageRequest.of(pageNum, pageSize));
        return page.getContent();
    }
}
