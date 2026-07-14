package com.sagar.backend.service;

import com.sagar.backend.entity.Employee;
import com.sagar.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

public Employee saveEmployee(Employee employee) {
    return employeeRepository.save(employee);
}
}