package com.kp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserAppApplication {
    
    public UserAppApplication()
    {
        System.out.println("Spring Boot Constructor");
    }

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

}
