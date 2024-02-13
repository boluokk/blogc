package org.bluo.service.resource.service;

import org.bluo.service.resource.pojo.Major;
import org.springframework.stereotype.Service;

/**
 * @author boluo
 * @date 2024/01/29
 */
@Service
public interface MajorService {
    Major findMajorByMajorCode(String majorCode);
}
