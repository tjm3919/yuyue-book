package com.trkj.yuyueredis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Druid数据源的配置类，但我们在nacos上配置DruidDataSource相关属性后，spring其实会自动为需要数据源的对象进行注入Druid数据源。
 * 也就是说这个类以及DataSourceConfig类中的public DruidDataSource druidDataSource(){}方法可以不用，但如果需要针对该微
 * 服务在数据源配置上有不同的配置，如每个微服务需要连接到不同的数据库，这时就可以在该配置中或都是直接在DataSourceConfig类中进行配置
 *
 * @Autowired private DataSource dataSource;
 */
@ConfigurationProperties(prefix = "spring.datasource.druid")
@Configuration
@Data
public class DruidDataSourceProperties {
    private String dbType;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
}
