package com.kamil.excavation;

import com.kamil.excavation.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class ExcavationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcavationApplication.class, args);
	}

}
