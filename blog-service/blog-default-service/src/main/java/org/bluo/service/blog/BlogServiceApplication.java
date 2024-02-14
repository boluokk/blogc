package org.bluo.service.blog;

import org.bluo.common.rabbitmq.autoconfig.EnabledAmqpMessageConverterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author boluo
 * @date 2024/02/04
 */
@SpringBootApplication
@EnabledAmqpMessageConverterConfig
@EnableFeignClients(basePackages = "org.bluo.service.esblog")
public class BlogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class);
    }
}
