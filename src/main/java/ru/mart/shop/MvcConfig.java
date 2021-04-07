package ru.mart.shop;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/META-INF/resources/", "classpath:/resources/",
	            "classpath:/static/", "classpath:/public/"
	    };
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	      registry
	                .addResourceHandler("/**")
	                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	    }
	
	 @Bean
	   public ViewResolver viewResolver() {
	      InternalResourceViewResolver bean = new InternalResourceViewResolver();
	      
	      bean.setPrefix("WEB-INF/view/");
	      bean.setSuffix(".html");

	      return bean;
	   }

}
