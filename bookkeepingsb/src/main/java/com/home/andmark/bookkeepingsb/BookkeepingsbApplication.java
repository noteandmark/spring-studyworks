package com.home.andmark.bookkeepingsb;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.home.andmark.bookkeepingsb.model"})
@EnableJpaRepositories(basePackages = "com.home.andmark.bookkeepingsb.repository")
//@EntityScan("com.home.andmark.bookkeepingsb.model")
public class BookkeepingsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookkeepingsbApplication.class, args);
	}

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}
}
