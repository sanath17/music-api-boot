package com.music.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.music.api.config.WebConfig;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@Import({ WebConfig.class })
public class Application {

	public static void main(String[] args) {
		 
		SpringApplication.run(Application.class, args);
	}
}