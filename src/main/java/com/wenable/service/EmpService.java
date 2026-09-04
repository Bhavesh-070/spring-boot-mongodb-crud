package com.wenable.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenable.dao.EmployeeDao;
import com.wenable.entity.Employee;

/**
 * Service layer for employee CRUD, delegating persistence to {@link EmployeeDao}.
 */
@Service
public class EmpService {

	private static final Logger log = LoggerFactory.getLogger(EmpService.class);

	@Autowired
	EmployeeDao employeeDao;

	/**
	 * Persists a new employee.
	 *
	 * @param emp the employee to save
	 * @return the saved employee, including its generated identifier
	 */
	public Employee saveEmp(Employee emp) {
		return employeeDao.save(emp);
	}

	/**
	 * Applies new values to the employee with the given identifier.
	 *
	 * <p>Does nothing if no employee carries that identifier.
	 *
	 * @param empid the identifier of the employee to update
	 * @param emp   the employee carrying the new name, number and designation
	 */
	public void updateEmp(String empid, Employee emp) {
		employeeDao.updateEmp(empid, emp);
	}

	/**
	 * Removes every employee document from the collection.
	 */
	public void deleteEmp() {
		log.info("All employees deleted");
		employeeDao.deleteAll();
	}

	/**
	 * Removes a single employee.
	 *
	 * @param empid the identifier of the employee to delete
	 */
	public void deleteEmpById(String empid) {
		employeeDao.deleteEmpById(empid);
	}

	/**
	 * Returns every stored employee.
	 *
	 * @return all employees, or an empty list when none exist
	 */
	public List<Employee> getEmp() {
		return employeeDao.findAllEmp();
	}

	/**
	 * Looks up a single employee by identifier.
	 *
	 * @param empId the identifier to search for
	 * @return the matching employee, or an empty {@link Optional} if none exists
	 */
	public Optional<Employee> getEmpById(String empId) {
		return employeeDao.getEmpById(empId); 
	}

}

