package com.example.ac2aw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ac2AwApplication {

   @Bean
   CommandLineRunner initDatabase() {
      return args -> {
         // Initialization logic can be added here if needed
      };
   }

	public static void main(String[] args) {
		SpringApplication.run(Ac2AwApplication.class, args);
	}

}
