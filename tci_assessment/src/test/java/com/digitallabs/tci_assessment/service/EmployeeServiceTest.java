package com.digitallabs.tci_assessment.service;

import com.digitallabs.tci_assessment.dto.EmployeeDTO;
import com.digitallabs.tci_assessment.dto.EmployeeListDTO;
import com.digitallabs.tci_assessment.dto.GetResponse;
import com.digitallabs.tci_assessment.entity.Department;
import com.digitallabs.tci_assessment.entity.Employee;
import com.digitallabs.tci_assessment.repository.DepartmentRepo;
import com.digitallabs.tci_assessment.repository.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private DepartmentRepo deptRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEmployee(){

        EmployeeDTO empDTO1 = new EmployeeDTO("raj", "accounts", 5000d, "INR", LocalDate.of(2022, 5, 20), LocalDate.of(2023, 5, 20));
        EmployeeDTO empDTO2 = new EmployeeDTO("pratap", "hr", 7000d, "USD", LocalDate.of(2021, 1, 1), LocalDate.of(2023, 5, 20));

        EmployeeListDTO empList = new EmployeeListDTO(List.of(empDTO1, empDTO2));

        Department dept = new Department();
        dept.setDeptName("accounts");

        when(deptRepo.findByDeptName("accounts")).thenReturn(Optional.of(dept));
        employeeService.saveEmployee(empList);

        verify(employeeRepo, times(2)).save(any(Employee.class));
    }

    @Test
    void testGetEligibleEmployees(){

        Department dept1 = new Department();
        dept1.setDeptName("accounts");

        Department dept2 = new Department();
        dept2.setDeptName("hr");

        Employee emp1 = new Employee(1l,"Alice", 4000.0, "USD", LocalDate.of(2020, 5, 20), LocalDate.of(2021, 4, 15), dept1);
        Employee emp2 = new Employee(2l,"Bob", 3000.0, "USD", LocalDate.of(2021, 4, 15), LocalDate.of(2023, 4, 15), dept2);
        Employee emp3 = new Employee(3l,"Charlie", 5000.0, "INR", LocalDate.of(2021, 3, 10), LocalDate.of(2025, 4, 15), dept1);

        when(this.employeeRepo.findEligibleEmployees(any(LocalDate.class))).thenReturn(List.of(emp1, emp2, emp3));

        GetResponse response = this.employeeService.getEligibleEmployees(LocalDate.of(2023, 4, 15));

        // Verify the response structure
        assertEquals(2, response.getData().size(), "Expected two currency groups in the response");

    }
}
