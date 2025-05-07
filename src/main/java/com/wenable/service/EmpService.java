package com.wenable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenable.dao.EmployeeDao;
import com.wenable.entity.Employee;
import com.wenable.repository.EmployeeRepository;

@Service
public class EmpService {

	@Autowired
	EmployeeDao employeeDao;

	public Employee saveEmp(Employee emp) {
		return employeeDao.save(emp);

	}

	public void updateEmp(String empid, Employee emp) {
		employeeDao.updateEmp(empid, emp);

	}

	public void deleteEmp() {
		System.out.println("All employees deleted ");
		employeeDao.deleteAll();
	}

	public void deleteEmpById(String empid) {
		employeeDao.deleteEmpById(empid);
	}

	public List<Employee> getEmp() {
		return employeeDao.findAllEmp();
	}

	public Optional<Employee> getEmpById(String empId) {
		return employeeDao.getEmpById(empId); 

	}

}
