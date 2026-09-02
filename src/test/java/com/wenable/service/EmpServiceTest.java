package com.wenable.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wenable.dao.EmployeeDao;
import com.wenable.entity.Employee;

@ExtendWith(MockitoExtension.class)
class EmpServiceTest {

	@Mock
	private EmployeeDao employeeDao;

	@InjectMocks
	private EmpService empService;

	private Employee emp1;
	private Employee emp2;

	@BeforeEach
	void setUp() {
		emp1 = new Employee("John Doe", "EMP001", "Developer");
		emp1.setEmpId("101");

		emp2 = new Employee("Jane Smith", "EMP002", "Manager");
		emp2.setEmpId("102");
	}

	@Test
	void testSaveEmp() {
		when(employeeDao.save(emp1)).thenReturn(emp1);

		Employee saved = empService.saveEmp(emp1);

		assertNotNull(saved);
		assertEquals("John Doe", saved.getEmpName());
		verify(employeeDao, times(1)).save(emp1);
	}

	@Test
	void testUpdateEmp() {
		empService.updateEmp("101", emp1);

		verify(employeeDao, times(1)).updateEmp("101", emp1);
	}

	@Test
	void testDeleteEmp() {
		empService.deleteEmp();

		verify(employeeDao, times(1)).deleteAll();
	}

	@Test
	void testDeleteEmpById() {
		empService.deleteEmpById("101");

		verify(employeeDao, times(1)).deleteEmpById("101");
	}

	@Test
	void testGetEmp() {
		when(employeeDao.findAllEmp()).thenReturn(Arrays.asList(emp1, emp2));

		List<Employee> list = empService.getEmp();

		assertEquals(2, list.size());
		verify(employeeDao, times(1)).findAllEmp();
	}

	@Test
	void testGetEmpById() {
		when(employeeDao.getEmpById("101")).thenReturn(Optional.of(emp1));

		Optional<Employee> result = empService.getEmpById("101");

		assertTrue(result.isPresent());
		assertEquals("John Doe", result.get().getEmpName());
		verify(employeeDao, times(1)).getEmpById("101");
	}
}
