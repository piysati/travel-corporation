package com.digitallabs.tci_assessment.service;

import com.digitallabs.tci_assessment.dto.EmployeeDTO;
import com.digitallabs.tci_assessment.dto.EmployeeListDTO;
import com.digitallabs.tci_assessment.dto.GetResponse;

import java.time.LocalDate;

public interface EmployeeService {
    void saveSingleEmployee(EmployeeDTO employeeDTO);
    void saveEmployee(EmployeeListDTO employeeRequests);
    GetResponse getEligibleEmployees(LocalDate requestDate);
}
