package com.wenable.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wenable.entity.Employee;

/**
 * Spring Data MongoDB repository for {@link Employee} documents.
 *
 * <p>Inherits the standard CRUD and pagination operations from {@link MongoRepository}.
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
