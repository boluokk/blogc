package org.bluo.service.user.service;


import global.pojo.User;

/**
 * @author boluo
 * @date 2024/01/27
 */
public interface UserService {
    User login(String username, String password);
    boolean register(User user);
}
