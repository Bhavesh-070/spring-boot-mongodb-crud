package com.wenable.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wenable.entity.Employee;

/**
 * Persistence operations for {@link Employee} records.
 */
public interface EmployeeDao{

	/**
	 * Persists a new employee.
	 *
	 * @param emp the employee to save
	 * @return the saved employee, including its generated identifier
	 */
	Employee save(Employee emp);

	/**
	 * Applies new values to the employee with the given identifier.
	 *
	 * @param empid the identifier of the employee to update
	 * @param emp   the employee carrying the new values
	 */
	public void updateEmp(String empid, Employee emp);

	/**
	 * Removes every employee document from the collection.
	 */
	void deleteAll();
	
	/**
	 * Removes a single employee.
	 *
	 * @param empid the identifier of the employee to delete
	 */
	public void deleteEmpById(String empid);

	/**
	 * Returns every stored employee.
	 *
	 * @return all employees, or an empty list when none exist
	 */
	List<Employee> findAllEmp();
	
	/**
	 * Looks up a single employee by identifier.
	 *
	 * @param empid the identifier to search for
	 * @return the matching employee, or an empty {@link Optional} if none exists
	 */
	public Optional<Employee> getEmpById(String empid);
}
