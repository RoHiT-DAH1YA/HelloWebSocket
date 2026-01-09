package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.config.SpringConfig;

 @SpringBootApplication
public class HelloWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class, args);
	}

}
