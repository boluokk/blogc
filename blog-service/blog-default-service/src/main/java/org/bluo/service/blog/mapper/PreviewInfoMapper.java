package org.bluo.service.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluo.service.blog.pojo.PreviewInfo;

import java.util.List;

/**
 * 预览信息 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */

@Mapper
public interface PreviewInfoMapper extends BaseMapper<PreviewInfo> {
    PreviewInfo findByBlogId(int blogId);
    List<PreviewInfo> findByBlogIds(@Param("blogIds") List<Integer> blogIds);
}
