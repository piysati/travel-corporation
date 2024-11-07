package com.digitallabs.tci_assessment.service;

import com.digitallabs.tci_assessment.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    void saveEmployee(List<EmployeeDTO> employeeRequests);
    List<EmployeeDTO> getEligibleEmployees(LocalDate requestDate);
}
