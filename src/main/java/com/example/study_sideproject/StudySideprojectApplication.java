package com.example.study_sideproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudySideprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySideprojectApplication.class, args);
	}

}

