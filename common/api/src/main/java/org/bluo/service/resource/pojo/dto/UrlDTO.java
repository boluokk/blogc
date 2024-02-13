package org.bluo.service.resource.pojo.dto;

import lombok.Data;

/**
 * @author boluo
 * @date 2024/02/03
 */
@Data
public class UrlDTO {
    private String avatar;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
