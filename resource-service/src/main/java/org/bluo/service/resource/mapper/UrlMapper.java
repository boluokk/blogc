package org.bluo.service.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluo.service.resource.pojo.Url;

/**
 * 媒体 持久层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Mapper
public interface UrlMapper extends BaseMapper<Url> {
    Url selectByUserId(@Param("userId") int userId);
}
