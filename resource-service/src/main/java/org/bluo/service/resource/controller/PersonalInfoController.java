package org.bluo.service.resource.controller;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.common.util.respons.Result;
import org.bluo.service.resource.ResourceClient;
import org.bluo.service.resource.pojo.Major;
import org.bluo.service.resource.pojo.School;
import org.bluo.service.resource.pojo.Url;
import org.bluo.service.resource.pojo.dto.UrlDTO;
import org.bluo.service.resource.service.impl.MajorServiceImpl;
import org.bluo.service.resource.service.impl.SchoolServiceImpl;
import org.bluo.service.resource.service.impl.UrlServiceImpl;
import org.springframework.web.bind.annotation.*;

import static org.bluo.service.resource.pojo.convert.UrlConvert.URL_CONVERT;

/**
 * 学校信息
 *
 * @author boluo
 * @date 2024/01/29
 */

@RestController
public class PersonalInfoController implements ResourceClient {

    @Resource
    private SchoolServiceImpl schoolServiceImpl;
    @Resource
    private UrlServiceImpl urlServiceImpl;
    @Resource
    private MajorServiceImpl majorServiceImpl;


    /**
     * 获取学校信息
     *
     * @param schoolCode 学校代码
     * @return 学校信息
     */
    @Override
    @GetMapping("/user/info/school")
    public Result findSchool(@RequestParam("schoolCode") String schoolCode) {
        School school = schoolServiceImpl.findSchoolBySchoolCode(schoolCode);
        if (ObjectUtil.isNotNull(school)) {
            return Result.ok(school);
        }
        return Result.fail();
    }

    /**
     * 获取媒体信息
     *
     * @param userId 用户Id
     * @return 媒体信息
     */
    @Override
    @GetMapping("/user/info/url")
    public Result findUrl(@RequestParam("userId") int userId) {
        Url url = urlServiceImpl.findUrlByUserID(userId);
        if (ObjectUtil.isNotNull(url)) {
            return Result.ok(url);
        }
        return Result.fail();
    }

    /**
     * 获取专业信息
     *
     * @param majorCode 专业代码
     * @return 专业信息
     */
    @Override
    @GetMapping("/user/info/major")
    public Result findMajor(@RequestParam("majorCode") String majorCode) {
        Major major = majorServiceImpl.findMajorByMajorCode(majorCode);
        if (ObjectUtil.isNotNull(major)) {
            return Result.ok(major);
        }
        return Result.fail();
    }

    /**
     * 添加资源信息
     *
     * @param urlDTO 资源信息
     * @return 结果
     */
    @Override
    @PutMapping("/user/add/url")
    public Result addUrl(@RequestBody UrlDTO urlDTO) {
        if (urlServiceImpl.addUrl(URL_CONVERT.toUrl(urlDTO))) {
            return Result.ok("成功");
        }
        return Result.fail();
    }

}
