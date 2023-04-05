package com.trkj.yuyuebook;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * //@EnableDubbo整合了三个注解@EnableDubboConfig、@DubboComponentScan、@EnableDubboLifecycle。@EnableDubbo的功能都是由这三个注解完成的。
 * //@EnableDubboConfig引入类DubboConfigConfigurationRegistrar，将用于解析配置相关的类注册到spring容器；
 * //@DubboComponentScan引入类DubboComponentScanRegistrar，用于指定@Service扫描路径；
 * //@EnableDubboLifecycle引入类DubboLifecycleComponentRegistrar，注册了两个监听器到spring容器。
 * 一般我们需要配置@DubboComponentScan，定义@Service的扫描路径。如果不配置@DubboComponentScan，默认使用@EnableDubbo注解的类的包路径。
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class YuyueBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuyueBookApplication.class, args);
    }

}
