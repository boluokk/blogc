package org.bluo.service.user.util.password;

/**
 * @author boluo
 * @date 2024/02/04
 */
public enum InvalidPassword {
    USER_NOT_FOUND("用户不存在"),
    OLD_PASS_SAME_NEW_PASS("新旧密码相同"),
    OLD_PASSWORD_FAIL("旧密码错误"),
    UPDATE_FAIL("更新失败");
    private String message;

    InvalidPassword(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
