package com.digitallabs.tci_assessment.controller;

import com.digitallabs.tci_assessment.dto.EmployeeDTO;
import com.digitallabs.tci_assessment.dto.EmployeeListDTO;
import com.digitallabs.tci_assessment.dto.GetResponse;
import com.digitallabs.tci_assessment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/tci")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee-bonus")
    public ResponseEntity<String> addEmployees(@RequestBody EmployeeListDTO employees) {
        System.out.println("hi");
        this.employeeService.saveEmployee(employees);
        System.out.println("bye");
        return ResponseEntity.ok("Employees added successfully");
    }

    @PostMapping(value = "/employee-bonus/single", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSingleEmployees(@RequestBody EmployeeDTO employee) {
        this.employeeService.saveSingleEmployee(employee);

        return ResponseEntity.ok("Single Employee added successfully");
    }

    @GetMapping("/employee-bonus")
    public ResponseEntity<?> getEligibleEmployees(@RequestParam("date") String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        GetResponse responseReturned = this.employeeService.getEligibleEmployees(date);
        return new ResponseEntity(responseReturned, HttpStatus.OK);
    }
}

