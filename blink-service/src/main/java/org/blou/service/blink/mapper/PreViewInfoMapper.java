package org.blou.service.blink.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.bluo.service.blink.pojo.PreViewInfo;

/**
 * 动态预览 持久层
 *
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface PreViewInfoMapper extends BaseMapper<PreViewInfo> {
    PreViewInfo findByBlinkId(int blinkId);
}
