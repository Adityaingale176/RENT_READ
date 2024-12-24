package com.example.rentread.RentRead;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class RentReadApplication {

	private static Logger logger = LoggerFactory.getLogger(RentReadApplication.class);

	public static void main(String[] args) {
		logger.info("RentReadApplication has started");
		SpringApplication.run(RentReadApplication.class, args);
	}

	@GetMapping("/")
	public String welcome(){
		return "Hello from authenticated endpoint";
	}

	@GetMapping("/admins")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String welcomAdmin(){
		return "Hello from admin's endpoint";
	}
}
