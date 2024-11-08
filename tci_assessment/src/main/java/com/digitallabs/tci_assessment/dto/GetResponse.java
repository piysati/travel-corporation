package com.digitallabs.tci_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetResponse {
        private String errorMessage = "";
        private List<EmployeeBonusResponse> data;
}
