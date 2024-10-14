package com.potandr1977.mongoPoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class MongoPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoPocApplication.class, args);
	}

}
