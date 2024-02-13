package org.bluo.service.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluo.service.blog.pojo.Tag;

import java.util.List;

/**
 * 标签 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> findByBlogId(int blogId);

    List<Tag> findByBlogIds(@Param("blogIds") List<Integer> blogIds);
}
