package com.sagar.backend.controller;

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

   @PostMapping
public Employee saveEmployee(@RequestBody Employee employee) {
    return employeeService.saveEmployee(employee);
}
}