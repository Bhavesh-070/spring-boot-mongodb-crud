package com.wenable.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenable.entity.Employee;
import com.wenable.service.EmpService;

import jakarta.validation.Valid;

/**
 * REST controller exposing CRUD endpoints for {@link Employee} resources.
 * All endpoints are served under the base path {@code /emp}.
 */
@RequestMapping("/emp")
@RestController
public class EmpController {

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;

    /**
     * Creates a new employee.
     *
     * @param emp the employee to persist (validated request body)
     * @return the saved employee, including its generated id
     */
    @PostMapping
    public Employee addEmployee(@Validated @RequestBody Employee emp) {
        log.info("Creating new employee: {}", emp.getEmpName());
        return empService.saveEmp(emp);
    }

    /**
     * Updates an existing employee identified by id.
     *
     * @param empid the id of the employee to update
     * @param emp   the new employee data
     */
    @PutMapping("/{empid}")
    public void updateEmployee(@Valid @PathVariable String empid, @RequestBody Employee emp) {
        log.info("Updating employee with id {}", empid);
        empService.updateEmp(empid, emp);
    }

    /**
     * Deletes all employees.
     */
    @DeleteMapping("/all")
    public void deleteAllEmployees() {
        log.info("Deleting all employees");
        empService.deleteEmp();
    }

    /**
     * Deletes a single employee by id.
     *
     * @param empid the id of the employee to delete
     * @return a confirmation message
     */
    @DeleteMapping("/{empid}")
    public ResponseEntity<String> deleteEmpById(@PathVariable String empid) {
        log.info("Deleting employee with id: {}", empid);
        empService.deleteEmpById(empid);
        return ResponseEntity.ok("Employee with ID " + empid + " deleted successfully.");
    }

    /**
     * Returns all employees.
     *
     * @return the list of all employees
     */
    @GetMapping
    public List<Employee> getEmployees() {
        log.debug("Fetching all employees");
        return empService.getEmp();
    }

    /**
     * Returns a single employee by id.
     *
     * @param empId the id of the employee to fetch
     * @return the matching employee if present, otherwise an empty {@link Optional}
     */
    @GetMapping("/{empId}")
    public Optional<Employee> getEmpById(@PathVariable String empId) {
        log.debug("Fetching employee with id: {}", empId);
        return empService.getEmpById(empId);
    }
}
