package org.bluo.service.user.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.service.user.service.impl.FileUploadServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理
 *
 * @author boluo
 * @date 2024/02/03
 */

@RestController
public class FileUploadController {

    @Resource
    private FileUploadServiceImpl fileUploadServiceImpl;

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 文件名
     */
    @PutMapping("/user/upload/avatar")
    public Result uploadFile(@RequestParam("userId") int userId,
                             @RequestParam("file") MultipartFile multipartFile) {
        String ret = fileUploadServiceImpl.uploadAvatar(userId, multipartFile);
        if (ObjectUtil.isNotNull(ret)) {
            return Result.ok(ret);
        }
        return Result.fail();
    }
}
