package com.projetGL.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories("com.projetGL.restaurant.*")
//@ComponentScan(basePackages = { "com.projetGL.restaurant.*" })
//@EntityScan("com.projetGL.restaurant.*")
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

}
