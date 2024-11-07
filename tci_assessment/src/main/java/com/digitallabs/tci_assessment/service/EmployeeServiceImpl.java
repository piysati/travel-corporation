package com.digitallabs.tci_assessment.service;

import com.digitallabs.tci_assessment.dto.EmployeeBonusResponse;
import com.digitallabs.tci_assessment.dto.EmployeeDTO;
import com.digitallabs.tci_assessment.entity.Department;
import com.digitallabs.tci_assessment.entity.Employee;
import com.digitallabs.tci_assessment.repository.DepartmentRepo;
import com.digitallabs.tci_assessment.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public void saveEmployee(List<EmployeeDTO> employeeRequests) {
        employeeRequests.stream().map(empDTO -> {
            //convert dto to model and save.

            Employee emp = new Employee();
            emp.setEmpName(empDTO.getEmpName());
            emp.setAmount(empDTO.getAmount());
            emp.setCurrency(empDTO.getCurrency());
            emp.setJoiningDate(empDTO.getJoiningDate());
            emp.setExitDate(empDTO.getExitDate());

            Department dept = departmentRepo.findByDeptName(empDTO.getDepartment())
                    .orElseGet(() -> {
                        Department newDepartment = new Department();
                        newDepartment.setDeptName(empDTO.getDepartment());
                        return this.departmentRepo.save(newDepartment);
                    });
            emp.setDepartment(dept);

            return this.employeeRepo.save(emp);
        });
    }

    @Override
    public List<EmployeeDTO> getEligibleEmployees(LocalDate requestDate) {
        List<Employee> eligibleEmployees = this.employeeRepo.findEligibleEmployees(requestDate);
//        eligibleEmployees.stream().map(emp -> {
//
//        });
//        EmployeeBonusResponse empBonResp = new EmployeeBonusResponse();
//        empBonResp.setCurrency();
        return List.of();
    }

    public List<EmployeeDTO> getEligibleEmployeesUsingStream(LocalDate requestDate) {
        List<Employee> allEmployees = this.employeeRepo.findAll();
        List<Employee> eligibleEmployees = allEmployees.stream().filter(emp -> emp.getJoiningDate().isBefore(requestDate)).collect(Collectors.toList());
        List<EmployeeDTO> convertedDTO = eligibleEmployees.stream().map(e -> {
            EmployeeDTO empDTO = new EmployeeDTO();
            empDTO.setAmount(e.getAmount());
            empDTO.setCurrency(e.getCurrency());
            empDTO.setEmpName(e.getEmpName());
            empDTO.setDepartment(e.getDepartment().getDeptName());
            empDTO.setJoiningDate(e.getJoiningDate());
            empDTO.setExitDate(e.getExitDate());
            return empDTO;
        }).collect(Collectors.toList());
        return convertedDTO;
    }
}
