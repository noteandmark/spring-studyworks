package com.home.andmark.bookkeepingsb;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.home.andmark.bookkeepingsb.repository")
public class BookkeepingsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookkeepingsbApplication.class, args);
	}

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}
}
