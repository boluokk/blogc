package org.bluo.service.blog.service;

import org.bluo.service.blog.pojo.PreviewInfo;

import java.util.List;

/**
 * @author boluo
 * @date 2024/02/03
 */
public interface PreviewInfoService {
    List<PreviewInfo> getList(List<Integer> blogIds);
}
