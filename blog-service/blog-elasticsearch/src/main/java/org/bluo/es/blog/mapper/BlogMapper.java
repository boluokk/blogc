package org.bluo.es.blog.mapper;

import global.pojo.BlogDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/13
 */
public interface BlogMapper extends ElasticsearchRepository<BlogDoc, Long> {
    /**
     * 根据标题查询
     *
     * @param title 标题
     * @return 博客集合
     */
    List<BlogDoc> findByTitle(String title);

    /**
     * 根据标题或者描述，分页获取
     *
     * @param title       标题
     * @param description 描述
     * @param pageable    分页信息
     * @return 分页数据
     */
    Page<BlogDoc> findByTitleOrDescription(String title, String description, Pageable pageable);
}
