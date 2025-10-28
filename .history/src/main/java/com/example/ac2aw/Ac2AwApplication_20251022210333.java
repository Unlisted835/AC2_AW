package com.example.ac2aw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac2aw.repositories.DatabaseSeeding;

@SpringBootApplication
public class Ac2AwApplication {

   @Bean
   CommandLineRunner initDatabase(DatabaseSeeding seeding) {
      return args -> seeding.run();
   }

	public static void main(String[] args) {
		SpringApplication.run(Ac2AwApplication.class, args);
	}

}
