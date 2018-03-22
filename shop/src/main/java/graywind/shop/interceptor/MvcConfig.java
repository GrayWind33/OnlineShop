package graywind.shop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  
@EnableWebMvc  
@ComponentScan(basePackages = "graywind.shop.controller")  
@PropertySource(value = "classpath:application.properties",  
        ignoreResourceNotFound = true,encoding = "UTF-8")
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
    
    @Autowired  
    LoginInterceptor loginInterceptor; 
    
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        // 注册监控拦截器  
        registry.addInterceptor(loginInterceptor)  
                .addPathPatterns("/**")  
         .excludePathPatterns("/configuration/ui");  
  
    }
    
    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowedHeaders("*/*")  
                .allowedMethods("*")  
                .maxAge(120);  
    }  
}
