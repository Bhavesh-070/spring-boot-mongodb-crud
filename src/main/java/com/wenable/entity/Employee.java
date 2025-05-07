package com.wenable.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

public class Employee {

	@Id
	private String empId;
	
	 @NotBlank(message = "Employee name is required")
	private String empName;
	private String empNo;
	private String empDesignation;

	public Employee() {
		super();
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public Employee(String empName, String enpNo, String empDesignation) {
		super();
		this.empName = empName;
		this.empNo = enpNo;
		this.empDesignation = empDesignation;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", enpNo=" + empNo + ", empDesignation=" + empDesignation + "]";
	}

	
}
