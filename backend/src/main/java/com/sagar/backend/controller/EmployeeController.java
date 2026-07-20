package com.sagar.backend.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.math.BigDecimal;

import com.sagar.backend.dto.EmployeeRequest;
import com.sagar.backend.dto.EmployeeResponse;
import com.sagar.backend.entity.Employee;
import com.sagar.backend.service.EmployeeService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET ALL EMPLOYEES WITH PAGINATION & SORTING
    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return employeeService.getAllEmployees(page, size, sortBy, direction);
    }




    @GetMapping("/search")
public List<Employee> searchByFirstName(
        @RequestParam String firstName) {

    return employeeService.getEmployeesByFirstName(firstName);
}

@GetMapping("/search/lastname")
public List<Employee> searchByLastName(
        @RequestParam String lastName) {

    return employeeService.getEmployeesByLastName(lastName);
}




// SEARCH BY EMAIL
@GetMapping("/search/email")
public List<Employee> searchByEmail(
        @RequestParam String email) {

    return employeeService.getEmployeesByEmail(email);
}

// SEARCH BY DEPARTMENT
@GetMapping("/search/department")
public List<Employee> searchByDepartment(
        @RequestParam String departmentName) {

    return employeeService.getEmployeesByDepartment(departmentName);
}

// SEARCH BY ROLE
@GetMapping("/search/role")
public List<Employee> searchByRole(
        @RequestParam String roleName) {

    return employeeService.getEmployeesByRole(roleName);
}

// SEARCH BY SALARY RANGE
@GetMapping("/search/salary")
public List<Employee> searchBySalaryRange(
        @RequestParam BigDecimal minSalary,
        @RequestParam BigDecimal maxSalary) {

    return employeeService.getEmployeesBySalaryRange(minSalary, maxSalary);
}

// PARTIAL SEARCH
@GetMapping("/search/name")
public List<Employee> searchEmployee(
        @RequestParam String firstName) {

    return employeeService.searchEmployeeByFirstName(firstName);
}

    // POST USING DTO
    @PostMapping
    public EmployeeResponse saveEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.saveEmployee(request);
    }

    // GET EMPLOYEE BY ID (Entity)
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {

        return employeeService.getEmployeeById(id);
    }

    // GET EMPLOYEE BY ID (DTO)
    @GetMapping("/{id}/dto")
    public EmployeeResponse getEmployeeDto(@PathVariable Integer id) {

        return employeeService.getEmployeeResponseById(id);
    }

    // UPDATE USING DTO
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable Integer id,
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.updateEmployee(id, request);
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteEmployee(id);
        return "Employee deleted successfully.";
    }
}