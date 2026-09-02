package com.wenable.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenable.entity.Employee;
import com.wenable.service.EmpService;

@WebMvcTest(EmpController.class)
class EmpControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmpService empService;

	private ObjectMapper objectMapper;

	private Employee emp1;
	private Employee emp2;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();

		emp1 = new Employee("John Doe", "EMP001", "Developer");
		emp1.setEmpId("101");

		emp2 = new Employee("Jane Smith", "EMP002", "Manager");
		emp2.setEmpId("102");
	}

	@Test
	void testAddEmployee() throws Exception {
		when(empService.saveEmp(any(Employee.class))).thenReturn(emp1);

		mockMvc.perform(post("/emp")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.empId").value("101"))
				.andExpect(jsonPath("$.empName").value("John Doe"))
				.andExpect(jsonPath("$.empDesignation").value("Developer"));

		verify(empService, times(1)).saveEmp(any(Employee.class));
	}

	@Test
	void testUpdateEmployee() throws Exception {
		doNothing().when(empService).updateEmp(eq("101"), any(Employee.class));

		mockMvc.perform(put("/emp/101")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp1)))
				.andExpect(status().isOk());

		verify(empService, times(1)).updateEmp(eq("101"), any(Employee.class));
	}

	@Test
	void testDeleteAllEmployees() throws Exception {
		doNothing().when(empService).deleteEmp();

		mockMvc.perform(delete("/emp/all"))
				.andExpect(status().isOk());

		verify(empService, times(1)).deleteEmp();
	}

	@Test
	void testDeleteEmpById() throws Exception {
		doNothing().when(empService).deleteEmpById("101");

		mockMvc.perform(delete("/emp/101"))
				.andExpect(status().isOk())
				.andExpect(content().string("Employee with ID 101 deleted successfully."));

		verify(empService, times(1)).deleteEmpById("101");
	}

	@Test
	void testGetEmployees() throws Exception {
		when(empService.getEmp()).thenReturn(Arrays.asList(emp1, emp2));

		mockMvc.perform(get("/emp"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].empName").value("John Doe"))
				.andExpect(jsonPath("$[1].empName").value("Jane Smith"));

		verify(empService, times(1)).getEmp();
	}

	@Test
	void testGetEmpById() throws Exception {
		when(empService.getEmpById("101")).thenReturn(Optional.of(emp1));

		mockMvc.perform(get("/emp/101"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.empId").value("101"))
				.andExpect(jsonPath("$.empName").value("John Doe"));

		verify(empService, times(1)).getEmpById("101");
	}
}
