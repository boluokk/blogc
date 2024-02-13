package org.bluo.service.resource;

import org.bluo.common.util.respons.Result;
import org.bluo.service.resource.pojo.dto.UrlDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author boluo
 * @date 2024/01/29
 */
@FeignClient("resource-service")
public interface ResourceClient {

    @GetMapping("/user/info/school")
    Result findSchool(@RequestParam("schoolCode") String schoolCode);

    @GetMapping("/user/info/url")
    Result findUrl(@RequestParam("userId") int userId);

    @GetMapping("/user/info/major")
    Result findMajor(@RequestParam("majorCode") String majorCode);

    @PutMapping("/user/add/url")
    Result addUrl(@RequestBody UrlDTO urlDTO);
}
