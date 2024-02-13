package org.bluo.service.gateway.config;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义需要登录的uri
 *
 * @author boluo
 * @date 2024/01/30
 */

@ConfigurationProperties(prefix = "auth.service")
@Configuration
@Data
public class AuthFilterListProperties {
    private List<String> uriArrays = new ArrayList<>();
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
        if (ObjectUtil.isNotEmpty(uri)) {
            uriArrays.addAll(Arrays.asList(uri.split(",")));
        }
    }

    public List<String> getUriArrays() {
        return uriArrays;
    }

    public void setUriArrays(List<String> uriArrays) {
        this.uriArrays = uriArrays;
    }
}
