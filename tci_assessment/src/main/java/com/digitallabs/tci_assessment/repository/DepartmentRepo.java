package com.digitallabs.tci_assessment.repository;

import com.digitallabs.tci_assessment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findByDeptName(String name);
}
