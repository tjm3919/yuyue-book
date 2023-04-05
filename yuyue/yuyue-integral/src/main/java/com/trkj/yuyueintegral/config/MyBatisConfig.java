package com.trkj.yuyueintegral.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement //开启事务管理
@Slf4j
public class MyBatisConfig implements TransactionManagementConfigurer {
    @Value("${mybatis.yuyueintegral.type-aliases-package}")
    private String aliasesPackage;
    @Value("${mybatis.yuyueintegral.mapper-locations}")
    private String mapperLocations;
    @Autowired
    //默认情况下Spring生成的数据源的名字就叫dataSource
    //@Qualifier("druidDataSourc")
    private DataSource dataSource1;
    @PostConstruct
    public void init(){
        log.debug("mapperLocations:{}",mapperLocations);
    }
    /**
     * 初始化 mybatis sqlSessionFactory
     * @Param: dataSourceProxy  datasource proxy
     * @Return: SqlSessionFactory
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource1);
        DruidDataSource db=(DruidDataSource)dataSource1;
        log.debug("mybatisdataSource={},{},{}", db.getUrl(), db.getUsername());
        log.debug("mybatisMaxActive:{},mybatisremoveAbandonedTimeout:{}",db.getMaxActive(),db.getRemoveAbandonedTimeout());
        bean.setTypeAliasesPackage(aliasesPackage);
        //分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);
        //添加插件
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        //添加mapper操作数据库XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(mapperLocations));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*配置事务管理器*/
    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource1);
    }
}