package com.unow.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.interceptor
 *  @文件名:   WebConfig
 *  @创建者:   Unow
 *  @创建时间:  2018/12/18 8:54
 *  @描述：    TODO
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    //通过@Bean注解，将我们定义的拦截器注册到Spring容器中
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    //重写接口中的addInterceptors方法，添加自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginInterceptor()).addPathPatterns("/api/**");
    }
}
