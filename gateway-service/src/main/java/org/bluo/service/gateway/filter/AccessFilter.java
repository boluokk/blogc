package org.bluo.service.gateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import global.pojo.User;
import org.bluo.common.util.util.TokenUtil;
import org.bluo.service.gateway.config.AuthFilterListProperties;
import org.bluo.service.gateway.util.FailAccessHandle;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import jakarta.annotation.Resource;

/**
 * 访问过滤:
 * 1、请求头未携带token (访问了需要token的)
 *
 * @author boluo
 * @date 2024/01/30
 */
@Component
public class AccessFilter implements GlobalFilter, Ordered {
    @Resource
    private AuthFilterListProperties authFilterListProperties;

    private AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        String uri = exchange.getRequest().getPath().value();
        String token = exchange.getRequest().getHeaders().getFirst("token");
        JWT jwt = null;
        // 是否需要token，并且不存在token
        if (requireToken(uri)) {
            if (ObjectUtil.isEmpty(token)) {
                return FailAccessHandle.fail("未登录!", response);
            }
            // 校验token
            try {
                jwt = JWTUtil.parseToken(token);
            } catch (Exception e) {
                return FailAccessHandle.fail("登录信息缺失!", response);
            }
            // 是否过期
            if (TokenUtil.isExpired(jwt)) {
                return FailAccessHandle.fail("登录信息已经过期!", response);
            }
        }
        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    /**
     * 判定是否，需要token
     *
     * @param uri 请求uri
     * @return 结果
     */
    public boolean requireToken(String uri) {
        for (String mathUri : authFilterListProperties.getUriArrays()) {
            if (matcher.match(mathUri, uri)) {
                return true;
            }
        }
        return false;
    }
}
