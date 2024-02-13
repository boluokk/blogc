package org.bluo.service.resource.pojo.convert;

import org.bluo.service.resource.pojo.Url;
import org.bluo.service.resource.pojo.dto.UrlDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 转换器
 *
 * @author boluo
 * @date 2024/02/03
 */
@Mapper
public interface UrlConvert {
    UrlConvert URL_CONVERT = Mappers.getMapper(UrlConvert.class);

    Url toUrl(UrlDTO urlDTO);
}
