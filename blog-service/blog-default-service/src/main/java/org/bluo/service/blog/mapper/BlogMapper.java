package org.bluo.service.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluo.service.blog.pojo.Blog;

import java.util.List;

/**
 * 博客 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> findPreviewPage(IPage<Blog> page, @Param("userId") int userId);

    Blog findOne(@Param("blogId") int blogId);

    List<Blog> findByIds(List<Integer> ids);
}
