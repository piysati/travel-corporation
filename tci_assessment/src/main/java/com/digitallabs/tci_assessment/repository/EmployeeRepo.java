package com.digitallabs.tci_assessment.repository;

import com.digitallabs.tci_assessment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    //save



    Optional<Employee> findByEmpName(String empName);

    //get employee for bonus
    @Query("SELECT e FROM Employee e WHERE e.joiningDate <= :requestDate " +
            "AND (e.exitDate IS NULL OR e.exitDate >= :requestDate) " +
            "ORDER BY e.currency, e.empName")
    List<Employee> findEligibleEmployees(@Param("requestDate") LocalDate requestDate);
}
