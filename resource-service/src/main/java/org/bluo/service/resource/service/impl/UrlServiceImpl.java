package org.bluo.service.resource.service.impl;

import jakarta.annotation.Resource;
import org.bluo.service.resource.mapper.UrlMapper;
import org.bluo.service.resource.pojo.Url;
import org.bluo.service.resource.service.UrlService;
import org.springframework.stereotype.Service;

/**
 * 媒体 业务层
 *
 * @author boluo
 * @date 2024/01/29
 */
@Service
public class UrlServiceImpl implements UrlService {

    @Resource
    private UrlMapper urlMapper;

    @Override
    public Url findUrlByUserID(int userId) {
        return urlMapper.selectByUserId(userId);
    }

    @Override
    public boolean addUrl(Url url) {
        return urlMapper.insert(url) == 1;
    }


}
