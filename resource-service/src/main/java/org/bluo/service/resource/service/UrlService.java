package org.bluo.service.resource.service;

import org.bluo.service.resource.pojo.Url;

/**
 * @author boluo
 * @date 2024/01/29
 */
public interface UrlService {
    Url findUrlByUserID(int urlId);

    boolean addUrl(Url url);
}
