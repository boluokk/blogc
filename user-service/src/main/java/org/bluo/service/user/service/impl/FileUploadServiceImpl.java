package org.bluo.service.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.common.util.util.ResultUtil;
import org.bluo.service.resource.ResourceClient;
import org.bluo.service.resource.pojo.Url;
import org.bluo.service.resource.pojo.dto.UrlDTO;
import org.bluo.service.user.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import util.MinIOUtil;

/**
 * 文件处理 业务层
 *
 * @author boluo
 * @date 2024/02/03
 */

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private ResourceClient resourceClient;

    /**
     * 上传头像
     *
     * @param userId        用户Id
     * @param multipartFile
     * @return 头像文件名
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadAvatar(int userId, MultipartFile multipartFile) {
        Result result = resourceClient.findUrl(userId);
        // 1、如果有旧的，覆盖上传
        if (ResultUtil.success(result)) {
            JSON parse = JSONUtil.parse(JSONUtil.toJsonStr(result.getData()));
            String avatar = parse.toBean(Url.class).getAvatar();
            String put = MinIOUtil.put(avatar, multipartFile);
            if (ObjectUtil.isNotNull(put)) {
                return avatar;
            }
        } else {
            // 2、如果第一次上传
            String newAvatar = MinIOUtil.upload(multipartFile);
            if (ObjectUtil.isNotNull(newAvatar)) {
                UrlDTO urlDTO = new UrlDTO();
                urlDTO.setAvatar(newAvatar);
                urlDTO.setUserId(userId);
                // 3、数据库新增数据
                Result newResult = resourceClient.addUrl(urlDTO);
                if (!ResultUtil.success(newResult)) {
                    MinIOUtil.delete(newAvatar);
                }
                return newAvatar;
            }
        }
        return null;
    }
}
