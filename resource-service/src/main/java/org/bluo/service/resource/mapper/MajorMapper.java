package org.bluo.service.resource.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.bluo.service.resource.pojo.Major;

/**
 * 专业 持久层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    Major findByMajorCode(String majorCode);
}
