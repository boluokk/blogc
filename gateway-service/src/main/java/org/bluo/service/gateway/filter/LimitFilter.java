package org.bluo.service.gateway.filter;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.bluo.service.gateway.config.AddressConfig;
import org.bluo.service.gateway.util.FailAccessHandle;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.bluo.service.gateway.config.IpLimitConfig.LIMIT_COUNT;
import static org.bluo.service.gateway.config.IpLimitConfig.PREFIX_KEY;

/**
 * IP限流
 *
 * @author boluo
 * @date 2024/01/31
 */
@Component
public class LimitFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取IP地址
        String ipAddress = AddressConfig.getIpAddress(exchange.getRequest());

        if (!checkVisitCount(PREFIX_KEY + ipAddress)) {
            return FailAccessHandle.fail("请求过于频繁!", exchange.getResponse());
        }

        return chain.filter(exchange);
    }

    /**
     * 检查次数是否超过：比较简易，待完善
     *
     * @param key key
     * @return 结果
     */
    public boolean checkVisitCount(String key) {
        Integer count = redisTemplate.opsForValue().get(key);
        // 第一次访问, 直接返回
        if (ObjectUtil.isNull(count)) {
            redisTemplate.opsForValue().set(key, 1);
            return true;
        }
        // 超过访问次数
        if (count > LIMIT_COUNT) {
            return false;
        }
        // 更新访问次数
        redisTemplate.opsForValue().increment(key);
        return true;
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
