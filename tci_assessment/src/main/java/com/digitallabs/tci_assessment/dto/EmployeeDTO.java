package com.digitallabs.tci_assessment.dto;

import com.digitallabs.tci_assessment.entity.Department;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmployeeDTO {
    private String empName;
    private String department;
    private Double amount;
    private String currency;
    private LocalDate joiningDate;
    private LocalDate exitDate;

}
