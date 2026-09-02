package com.wenable.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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

import com.wenable.entity.Employee;
import com.wenable.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeDaoImpl employeeDao;

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
	void testSave() {
		when(employeeRepository.save(emp1)).thenReturn(emp1);

		Employee saved = employeeDao.save(emp1);

		assertNotNull(saved);
		assertEquals("John Doe", saved.getEmpName());
		verify(employeeRepository, times(1)).save(emp1);
	}

	@Test
	void testUpdateEmp_WhenFound() {
		when(employeeRepository.findById("101")).thenReturn(Optional.of(emp1));
		when(employeeRepository.save(any(Employee.class))).thenReturn(emp1);

		Employee updateDetails = new Employee("John Updated", "EMP001", "Senior Developer");
		employeeDao.updateEmp("101", updateDetails);

		verify(employeeRepository, times(1)).findById("101");
		verify(employeeRepository, times(1)).save(emp1);
	}

	@Test
	void testUpdateEmp_WhenNotFound() {
		when(employeeRepository.findById("999")).thenReturn(Optional.empty());

		Employee updateDetails = new Employee("Non Existent", "EMP999", "Tester");
		employeeDao.updateEmp("999", updateDetails);

		verify(employeeRepository, times(1)).findById("999");
		verify(employeeRepository, never()).save(any(Employee.class));
	}

	@Test
	void testDeleteAll() {
		employeeDao.deleteAll();

		verify(employeeRepository, times(1)).deleteAll();
	}

	@Test
	void testDeleteEmpById_WhenExists() {
		when(employeeRepository.existsById("101")).thenReturn(true);

		employeeDao.deleteEmpById("101");

		verify(employeeRepository, times(1)).existsById("101");
		verify(employeeRepository, times(1)).deleteById("101");
	}

	@Test
	void testDeleteEmpById_WhenNotExists() {
		when(employeeRepository.existsById("999")).thenReturn(false);

		employeeDao.deleteEmpById("999");

		verify(employeeRepository, times(1)).existsById("999");
		verify(employeeRepository, never()).deleteById("999");
	}

	@Test
	void testFindAllEmp() {
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1, emp2));

		List<Employee> list = employeeDao.findAllEmp();

		assertEquals(2, list.size());
		verify(employeeRepository, times(1)).findAll();
	}

	@Test
	void testGetEmpById() {
		when(employeeRepository.findById("101")).thenReturn(Optional.of(emp1));

		Optional<Employee> result = employeeDao.getEmpById("101");

		assertTrue(result.isPresent());
		assertEquals("John Doe", result.get().getEmpName());
		verify(employeeRepository, times(1)).findById("101");
	}
}
