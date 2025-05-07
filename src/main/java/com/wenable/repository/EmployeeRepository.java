package com.wenable.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wenable.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
