package com.example.demo;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.demo.controller.NoteController;
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);
	}

}
