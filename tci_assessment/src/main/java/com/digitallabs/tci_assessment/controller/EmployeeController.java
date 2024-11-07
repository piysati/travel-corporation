package com.digitallabs.tci_assessment.controller;

import com.digitallabs.tci_assessment.dto.EmployeeDTO;
import com.digitallabs.tci_assessment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tci/employee-bonus")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<String> addEmployees(@RequestBody List<EmployeeDTO> employees) {
        this.employeeService.saveEmployee(employees);
        return ResponseEntity.ok("Employees added successfully");
    }

//    @GetMapping("/{date}")
//    public ResponseEntity<Map<String, Object>> getEligibleEmployees(@RequestParam LocalDate date) {
//        List<EmployeeResponse> eligibleEmployees = this.employeeService.getEligibleEmployees(date);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("errorMessage", "");
//        response.put("data", eligibleEmployees);
//
//        return ResponseEntity.ok(response);
//    }
}

