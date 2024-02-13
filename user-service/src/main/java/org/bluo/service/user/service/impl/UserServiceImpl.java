package org.bluo.service.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import global.pojo.User;
import jakarta.annotation.Resource;
import org.bluo.service.user.mapper.UserMapper;
import org.bluo.service.user.service.UserService;
import org.bluo.service.user.util.password.InvalidPassword;
import org.springframework.stereotype.Service;

import static org.bluo.service.user.util.password.InvalidPassword.*;


/**
 * 用户 业务层
 *
 * @author boluo
 * @date 2024/01/27
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (ObjectUtil.isNull(user)) {
            return null;
        }
        if (!user.getPassword().equals(SecureUtil.md5(password))) {
            return null;
        }
        return user;
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean register(User user) {
        user.setPassword(SecureUtil.md5(user.getPassword()));
        return userMapper.insert(user) == 1;
    }

    /**
     * 查询用户
     *
     * @param username 用户名称
     * @return 用户
     */
    public User findUserByUserName(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 修改密码
     *
     * @param userId      用户Id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 结果
     */
    public InvalidPassword updatePassword(int userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        String DBPassword = user.getPassword();
        // 新旧密码相同
        if (oldPassword.equals(newPassword)) {
            return OLD_PASS_SAME_NEW_PASS;
        }
        // 旧密码错误
        if (!DBPassword.equals(SecureUtil.md5(oldPassword))) {
            return OLD_PASSWORD_FAIL;
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(SecureUtil.md5(newPassword));
        // 更新失败
        if (userMapper.updateById(newUser) != 1) {
            return UPDATE_FAIL;
        }
        return null;
    }
}
