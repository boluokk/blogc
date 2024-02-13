package org.bluo.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author boluo
 * @date 2024/01/27
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"org.bluo.service.resource"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }
}
