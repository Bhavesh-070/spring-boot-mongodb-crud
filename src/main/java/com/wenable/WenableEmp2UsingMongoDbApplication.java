package com.wenable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the employee CRUD REST API.
 *
 * <p>Boots a Spring Boot application exposing create, read, update and delete endpoints
 * for employees under {@code /emp}, persisted in MongoDB.
 */
@SpringBootApplication
public class WenableEmp2UsingMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(WenableEmp2UsingMongoDbApplication.class, args);
	}

}
