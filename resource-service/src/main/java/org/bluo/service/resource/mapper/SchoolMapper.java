package org.bluo.service.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.bluo.service.resource.pojo.School;

/**
 * 学校 持久层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Mapper
public interface SchoolMapper extends BaseMapper<School> {
    School selectBySchoolCode(String schoolCode);
}
