package com.ril;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.ril.entity.User;
import com.ril.model.UserDao;

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
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
	@Bean
	public CommandLineRunner demo(UserDao repository) {
		return (args) -> {
			log.info("user found with findByLoginAndPassword('user1'):");
			log.info("--------------------------------------------");
			User user=repository.findByLoginAndPassword("user1", "aa1122");
			if (user!=null)
				log.info(user.toString());
				else
					log.info("user1 non trouv�");
					
			log.info("");
		};
	}
}