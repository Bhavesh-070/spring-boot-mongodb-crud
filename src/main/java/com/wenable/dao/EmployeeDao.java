package com.wenable.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wenable.entity.Employee;

public interface EmployeeDao{

	Employee save(Employee emp);

	public void updateEmp(String empid, Employee emp);

	void deleteAll();
	
	public void deleteEmpById(String empid);

	List<Employee> findAllEmp();
	
	public Optional<Employee> getEmpById(String empid);
}
