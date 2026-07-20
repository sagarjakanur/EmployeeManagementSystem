package com.sagar.backend.controller;

import jakarta.validation.Valid;

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