package com.kamil.excavation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ExcavationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcavationApplication.class, args);
	}

}
