package org.bluo.service.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.bluo.service.user.pojo.PersonalInfo;

/**
 * 用户个人信息 持久层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Mapper
public interface PersonalInfoMapper extends BaseMapper<PersonalInfo> {
    PersonalInfo selectByUserId(int userId);
}
