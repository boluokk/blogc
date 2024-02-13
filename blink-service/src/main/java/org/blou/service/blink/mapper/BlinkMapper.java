package org.blou.service.blink.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.bluo.service.blink.pojo.Blink;

/**
 * 动态 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface BlinkMapper extends BaseMapper<Blink> {
    Blink findByUserId(int userId);
}
