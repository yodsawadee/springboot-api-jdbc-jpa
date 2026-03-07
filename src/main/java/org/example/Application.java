package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication()
public class Application {

	@PostConstruct
	public void init() {
		System.out.println("Original timezone is " + TimeZone.getDefault().getDisplayName() + ": " + new Date());
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
		System.out.println("Spring boot application running in " + TimeZone.getDefault().getDisplayName() + " timezone: " + new Date());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}

