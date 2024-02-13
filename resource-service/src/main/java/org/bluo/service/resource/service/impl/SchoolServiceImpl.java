package org.bluo.service.resource.service.impl;

import org.bluo.service.resource.mapper.SchoolMapper;
import org.bluo.service.resource.pojo.School;
import org.bluo.service.resource.service.SchoolService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 学校 业务层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public School findSchoolBySchoolCode(String schoolCode) {
        return schoolMapper.selectBySchoolCode(schoolCode);
    }
}
