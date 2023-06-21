package com.jdms.itemy.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "com.jdms.itemy")
public class ItemyApplication {
    public static void main(String... args) {
        SpringApplication.run(ItemyApplication.class, args);
    }
}
