package ru.mart.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ru.mart.shop.controller.ShopController;

@SpringBootApplication(scanBasePackages = "repository")
@ComponentScan(basePackageClasses = ShopController.class)
public class SpringConfig {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class, args);
	}
	
	
}