package org.bluo.service.user.pojo.convert;

import org.bluo.service.resource.pojo.Major;
import org.bluo.service.resource.pojo.School;
import org.bluo.service.resource.pojo.Url;
import org.bluo.service.user.pojo.PersonalInfo;
import org.bluo.service.user.pojo.vo.PersonalInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * 用户信息 转换器
 *
 * @author boluo
 * @date 2024/01/29
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonalInfoConvert {

    @Mapping(target = "schoolName", source = "school.name")
    @Mapping(target = "majorName", source = "major.name")
    @Mapping(target = "userId", source = "personalInfo.userId")
    PersonalInfoVO toPersonalInfoVO(PersonalInfo personalInfo, School school, Major major, Url url);
}

