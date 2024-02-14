package org.bluo.service.blog.convert;

import global.pojo.BlogDoc;
import org.bluo.service.blog.pojo.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BlogDocConvert {
    BlogDoc toBlogDoc(Blog blog);
}
