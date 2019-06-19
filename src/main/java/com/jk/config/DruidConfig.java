package com.jk.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {


    /**
     * Druid配置类  确认数据源的链接，和超时时自动断开连接
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(destroyMethod="close", initMethod = "init")
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     *  配置监控服务器
     **/
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        //设置白名单
        initParams.put("allow","127.0.0.1");
        //黑名单
        initParams.put("deny","192.168.0.2");
        // druid后台管理员用户
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123");
        // 是否能够重置数据
        initParams.put("resetEnable", "false");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     *  配置web监控的过滤器
     **/
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        bean.addUrlPatterns("/*");
        Map<String,String> initParams = new HashMap<>();
        // 忽略过滤格式
        initParams.put("exclusions","*.js,*.css,*.icon,*.png,*.jpg,/druid/*");
        bean.setInitParameters(initParams);
        return  bean;
    }
}
