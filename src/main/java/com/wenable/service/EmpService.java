package com.wenable.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenable.dao.EmployeeDao;
import com.wenable.entity.Employee;

@Service
public class EmpService {

	private static final Logger log = LoggerFactory.getLogger(EmpService.class);

	@Autowired
	EmployeeDao employeeDao;

	public Employee saveEmp(Employee emp) {
		return employeeDao.save(emp);
	}

	public void updateEmp(String empid, Employee emp) {
		employeeDao.updateEmp(empid, emp);
	}

	public void deleteEmp() {
		log.info("All employees deleted");
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

