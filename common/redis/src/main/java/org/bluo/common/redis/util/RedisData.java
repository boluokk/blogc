package org.bluo.common.redis.util;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author boluo
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
