package org.bluo.service.user.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author boluo
 * @date 2024/02/03
 */
public interface FileUploadService {
    String uploadAvatar(int userId, MultipartFile multipartFile);
}
