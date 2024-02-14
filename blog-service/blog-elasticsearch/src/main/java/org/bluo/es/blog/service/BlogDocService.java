package org.bluo.es.blog.service;

import global.pojo.BlogDoc;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */
public interface BlogDocService {
    List<BlogDoc> getPage(String title, String description, int pageNum, int pageSize);
}
