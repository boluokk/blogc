package org.bluo.service.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bluo.service.blog.pojo.Type;

import java.util.List;

/**
 * 类型 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    List<Type> selectByIds(@Param("ids") List<Integer> ids);
}
