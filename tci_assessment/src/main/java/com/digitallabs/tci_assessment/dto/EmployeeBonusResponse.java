package com.digitallabs.tci_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class EmployeeBonusResponse {
    private String currency;
    private List<EmployeeBonusAttribute> employeeBonusAttributeList;

}
