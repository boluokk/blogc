package org.bluo.service.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import global.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户 持久层
 *
 * @author boluo
 * @date 2024/01/27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);
}
