package com.digitallabs.tci_assessment.dto;

import com.digitallabs.tci_assessment.entity.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmployeeDTO {

    private String empName;

    private String department;
    private Double amount;
    private String currency;
    @JsonFormat(pattern = "MMM-dd-yyyy", locale = "en")
    private LocalDate joiningDate;
    @JsonFormat(pattern = "MMM-dd-yyyy", locale = "en")
    private LocalDate exitDate;

}
