package org.bluo.service.user.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.common.util.util.ResultUtil;
import org.bluo.service.resource.ResourceClient;
import org.bluo.service.resource.pojo.Major;
import org.bluo.service.resource.pojo.School;
import org.bluo.service.resource.pojo.Url;
import org.bluo.service.user.mapper.PersonalInfoMapper;
import org.bluo.service.user.pojo.PersonalInfo;
import org.bluo.service.user.pojo.convert.PersonalInfoConvert;
import org.bluo.service.user.pojo.vo.PersonalInfoVO;
import org.bluo.service.user.service.PersonalInfoService;
import org.springframework.stereotype.Service;
import util.MinIOUtil;

/**
 * 用户信息 业务层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {
    @Resource
    private PersonalInfoMapper personalInfoMapper;
    @Resource
    private ResourceClient resourceClient;
    @Resource
    private PersonalInfoConvert personalInfoConvert;

    @Override
    public PersonalInfoVO findPersonalInfo(int userId) {
        // 查询用户信息
        PersonalInfo personalInfo = personalInfoMapper.selectByUserId(userId);
        if (ObjectUtil.isNotNull(personalInfo)) {
            // 查询用户学校信息
            Result school = resourceClient.findSchool(personalInfo.getSchoolCode());
            // 查询用户专业信息
            Result major = resourceClient.findMajor(personalInfo.getMajorCode());
            // 查询用户媒体信息
            Result url = resourceClient.findUrl(userId);
            // 转换成链接
            Url data = null;
            if (ResultUtil.success(url)) {
                data = JSONUtil.toBean(JSONUtil.toJsonStr(url.getData()), Url.class);
                String preview = MinIOUtil.preview(data.getAvatar());
                data.setAvatar(preview);
            }
            // 转换成VO
            return personalInfoConvert.toPersonalInfoVO(personalInfo,
                    Convert.convert(School.class, school.getData()),
                    Convert.convert(Major.class, major.getData()),
                    Convert.convert(Url.class, data));
        }
        return null;
    }
}
