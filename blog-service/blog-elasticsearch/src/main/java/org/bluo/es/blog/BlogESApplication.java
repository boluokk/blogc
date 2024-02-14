package org.bluo.es.blog;

import org.bluo.common.rabbitmq.autoconfig.EnabledAmqpMessageConverterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author boluo
 * @version 1.0.0
 * @date 2024/02/13
 */

@SpringBootApplication
@EnabledAmqpMessageConverterConfig
public class BlogESApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogESApplication.class);
    }
}
