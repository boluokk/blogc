package org.bluo.service.user.service;

import org.bluo.service.user.pojo.PersonalInfo;
import org.bluo.service.user.pojo.vo.PersonalInfoVO;

/**
 *
 * @author boluo
 * @date 2024/01/29
 */
public interface PersonalInfoService {
    PersonalInfoVO findPersonalInfo(int userId);
}
