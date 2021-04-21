package com.dummybot.got;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dummybot.got*")
public class GotApplication {

	public static void main(String[] args) {
		SpringApplication.run(GotApplication.class, args);
	}

}
