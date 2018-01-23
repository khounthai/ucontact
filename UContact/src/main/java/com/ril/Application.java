package com.ril;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.util.TemplateModeUtils;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public ClassLoaderTemplateResolver yourTemplateResolver() {
        ClassLoaderTemplateResolver yourTemplateResolver = new ClassLoaderTemplateResolver();
        yourTemplateResolver.setPrefix("com/ril/templates/");
        yourTemplateResolver.setSuffix(".html");
        yourTemplateResolver.setCharacterEncoding("UTF-8");
        yourTemplateResolver.setOrder(0);  // this is iportant. This way spring 
                                            //boot will listen to both places 0 
                                            //and 1
        return yourTemplateResolver;
	}
    
    @Configuration
    public class WebMvcConfig extends WebMvcConfigurationSupport {
        @Override
        protected void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new ThymeleafLayoutInterceptor());
        }
    }
}