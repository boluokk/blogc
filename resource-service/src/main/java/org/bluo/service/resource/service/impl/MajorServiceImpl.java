package org.bluo.service.resource.service.impl;

import org.bluo.service.resource.mapper.MajorMapper;
import org.bluo.service.resource.pojo.Major;
import org.bluo.service.resource.service.MajorService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 专业 业务层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Resource
    private MajorMapper majorMapper;

    @Override
    public Major findMajorByMajorCode(String majorCode) {
        return majorMapper.findByMajorCode(majorCode);
    }
}
