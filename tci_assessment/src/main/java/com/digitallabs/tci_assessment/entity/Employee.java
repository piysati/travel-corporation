package com.digitallabs.tci_assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Table(name = "employee")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "employee_name")
    private String empName;
    private Double amount;
    private String currency;
    //MMM-dd-yyyy
    private LocalDate joiningDate;
    private LocalDate exitDate;

    @ManyToOne
    private Department department;

}
