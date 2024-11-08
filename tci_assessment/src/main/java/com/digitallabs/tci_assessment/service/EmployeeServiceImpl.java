package com.digitallabs.tci_assessment.service;

import com.digitallabs.tci_assessment.dto.*;
import com.digitallabs.tci_assessment.entity.Department;
import com.digitallabs.tci_assessment.entity.Employee;
import com.digitallabs.tci_assessment.exception.ResourceDuplicateException;
import com.digitallabs.tci_assessment.repository.DepartmentRepo;
import com.digitallabs.tci_assessment.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public void saveSingleEmployee(EmployeeDTO empDTO) {
        Optional<Employee> employeeOptional = this.employeeRepo.findByEmpName(empDTO.getEmpName());
        if(employeeOptional.isEmpty())
            throw new ResourceDuplicateException("Resource Already Present");
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
        this.employeeRepo.save(emp);
    }

    @Override
    public void saveEmployee(EmployeeListDTO employeeRequests) {

        List<EmployeeDTO> employeeToBeAdded = employeeRequests.getEmployees();
        for (EmployeeDTO empDTO : employeeToBeAdded  ){
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

            this.employeeRepo.save(emp);
        }
    }

    @Override
    public GetResponse getEligibleEmployees(LocalDate requestDate) {
        List<Employee> eligibleEmployees = this.employeeRepo.findEligibleEmployees(requestDate);

        List<String> distinctCurrency = eligibleEmployees.stream().map(emp -> emp.getCurrency()).distinct().collect(Collectors.toList());

        Map<String, List<EmployeeBonusAttribute>> groupedByCurrency = eligibleEmployees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getCurrency,
                        Collectors.mapping(
                                emp -> new EmployeeBonusAttribute(emp.getEmpName(), emp.getAmount()),
                                Collectors.toList()
                        )
                ));

        List<EmployeeBonusResponse> responseGen = groupedByCurrency.entrySet().stream()
                .map(entry -> new EmployeeBonusResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new GetResponse("", responseGen);
    }
}
