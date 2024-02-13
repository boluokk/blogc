package org.bluo.service.gateway.config;

/**
 * IP 访问限制配置
 *
 * @author boluo
 * @date 2024/01/31
 */
public class IpLimitConfig {
    public static final String PREFIX_KEY = "limit_ip";
    // 限制次数10次
    public static final int LIMIT_COUNT = 10;
    // 过期时间
    public static final int EXPIRE_TIME_IN_SECONDS = 3;
}
