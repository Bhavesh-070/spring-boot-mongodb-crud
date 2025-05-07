package com.wenable.controller;

import java.util.List;
import java.util.Optional;

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

@RequestMapping("/emp")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping
    public Employee addEmployee(@Validated @RequestBody Employee emp) {
        return empService.saveEmp(emp);
    }

    @PutMapping("/{empid}")
    public void updateEmployee(@Valid @PathVariable String empid, @RequestBody Employee emp) {
    	System.out.println("Update employee method running ");
        empService.updateEmp(empid, emp);
    }

    @DeleteMapping("/all")
    public void deleteAllEmployees() {
    	System.out.println("Delete employee method running ");
        empService.deleteEmp();
    }

    @DeleteMapping("/{empid}")
    public ResponseEntity<String> deleteEmpById(@PathVariable String empid) {
        empService.deleteEmpById(empid);
        return ResponseEntity.ok("Employee with ID " + empid + " deleted successfully.");
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return empService.getEmp();
    }

    @GetMapping("/{empId}")
    public Optional<Employee> getEmpById(@PathVariable String empId) {
        return empService.getEmpById(empId);
    }
}

