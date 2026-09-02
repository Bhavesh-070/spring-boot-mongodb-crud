package com.wenable.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wenable.entity.Employee;
import com.wenable.repository.EmployeeRepository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee emp) {
	   return employeeRepository.save(emp);
	}

	@Override
	public void updateEmp(String empid, Employee emp) {
		Optional<Employee> optional = employeeRepository.findById(empid);
		if (optional.isPresent()) {
			Employee employee = optional.get();

			employee.setEmpDesignation(emp.getEmpDesignation());
			employee.setEmpName(emp.getEmpName());
			employee.setEmpNo(emp.getEmpNo());

			employeeRepository.save(employee);

			log.info("Employee updated with id {}", empid);
		} else {
			log.warn("Employee not found with id {}", empid);
		}
		
	}

	@Override
	public void deleteAll() {
		employeeRepository.deleteAll();
	}

	@Override
	public void deleteEmpById(String empid) {
		if (employeeRepository.existsById(empid)) {
			employeeRepository.deleteById(empid);
			log.info("Employee deleted with id {}", empid);
		} else {
			log.warn("No employee found with id {}", empid);
		}
	}

	@Override
	public List<Employee> findAllEmp() {
		 return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmpById(String empId) {
		return employeeRepository.findById(empId);
	}
	
}
