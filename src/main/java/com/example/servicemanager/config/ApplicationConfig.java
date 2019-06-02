package com.example.servicemanager.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // 启注解事务管理
//@MapperScan(basePackages = "com.example.servicemanager.mapp", sqlSessionFactoryRef = "SqlSessionFactory")
public class ApplicationConfig extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(this.getClass());

    }

//    //注册filter
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        TokenAuthorFilter tokenAuthorFilter = new TokenAuthorFilter();
//        registrationBean.setFilter(tokenAuthorFilter);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/mydemo/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//        return registrationBean;
//    }


    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {

            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);//设置驼峰命名规则
            }
        };
    }


}
