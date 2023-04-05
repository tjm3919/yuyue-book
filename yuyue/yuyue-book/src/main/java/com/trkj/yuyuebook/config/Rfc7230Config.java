package com.trkj.yuyuebook.config;
 
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置转义字符,解决当请求路径中特殊字符，解决高版本Tomcat对接口参数限制字符，解析失败从而出现RFC7230、RFC3986问题
 * @Version: 1.0
 **/
@Configuration
public class Rfc7230Config {
 
    /**
     * 解决Tomcat RFC7230问题
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            connector.setProperty("relaxedQueryChars", "|{}[](),/:;<=>?@[\\]{}\\");
            connector.setProperty("relaxedPathChars", "|{}[](),/:;<=>?@[\\]{}\\");
            connector.setProperty("rejectIllegalHeader", "false");
        });
 
        return factory;
    }
}