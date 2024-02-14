package org.bluo.common.rabbitmq.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AmqpMessageConverterConfig.class)
public @interface EnabledAmqpMessageConverterConfig {
}
