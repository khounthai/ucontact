package com.ril;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig 	extends WebMvcConfigurerAdapter {

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler(
	                "/webjars/**",
	                "/img/**",
	                "/css/**",
	                "/js/**",
	                "/less/**")
	                .addResourceLocations(
	                        "classpath:/META-INF/resources/webjars/",
	                        "classpath:/com/ril/img/",
	                        "classpath:/com/ril/css/",
	                        "classpath:/com/ril/js/",
	                        "classpath:/com/ril/less/");
	    }
	    
	    @Override
		public void addInterceptors(InterceptorRegistry registry) {
		    registry.addInterceptor(new ThymeleafLayoutInterceptor());
		}
	    
	 /*   @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/home").setViewName("home");
	        registry.addViewController("/").setViewName("home");
	        registry.addViewController("/hello").setViewName("hello");
	        registry.addViewController("/login").setViewName("login");
	    }*/

}