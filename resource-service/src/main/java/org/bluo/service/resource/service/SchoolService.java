package org.bluo.service.resource.service;

import org.bluo.service.resource.pojo.School;

/**
 * @author boluo
 * @date 2024/01/29
 */
public interface SchoolService {
    School findSchoolBySchoolCode(String schoolCode);
}
