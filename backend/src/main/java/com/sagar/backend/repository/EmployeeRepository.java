package com.sagar.backend.repository;

import com.sagar.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Search by First Name
    List<Employee> findByFirstName(String firstName);

    // Search by Last Name
    List<Employee> findByLastName(String lastName);

    // Search by Email
    List<Employee> findByEmail(String email);

    // Search by Department
    List<Employee> findByDepartmentDepartmentName(String departmentName);

    // Search by Role
    List<Employee> findByRoleRoleName(String roleName);

    // Search by Salary Range
    List<Employee> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary);

    // Partial Search
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

}