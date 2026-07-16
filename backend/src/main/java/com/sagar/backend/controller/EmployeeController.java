package com.sagar.backend.controller;

import com.sagar.backend.dto.EmployeeRequest;
import com.sagar.backend.dto.EmployeeResponse;
import com.sagar.backend.entity.Employee;
import com.sagar.backend.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // DTO POST API
    @PostMapping
    public EmployeeResponse saveEmployee(@RequestBody EmployeeRequest request) {
        return employeeService.saveEmployee(request);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/{id}/dto")
    public EmployeeResponse getEmployeeDto(@PathVariable Integer id) {
        return employeeService.getEmployeeResponseById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id,
                                   @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully.";
    }
}