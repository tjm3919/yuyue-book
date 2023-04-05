package com.trkj.yuyueuser;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class YuyueUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuyueUserApplication.class, args);
    }

}
