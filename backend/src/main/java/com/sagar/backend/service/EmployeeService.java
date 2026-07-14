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


public Employee getEmployeeById(Integer id) {
    return employeeRepository.findById(id).orElse(null);
}




public Employee updateEmployee(Integer id, Employee employee) {

    Employee existingEmployee = employeeRepository.findById(id).orElse(null);

    if (existingEmployee == null) {
        return null;
    }

    existingEmployee.setFirstName(employee.getFirstName());
    existingEmployee.setLastName(employee.getLastName());
    existingEmployee.setEmail(employee.getEmail());
    existingEmployee.setPhone(employee.getPhone());
    existingEmployee.setSalary(employee.getSalary());
    existingEmployee.setHireDate(employee.getHireDate());
    existingEmployee.setDepartment(employee.getDepartment());
    existingEmployee.setRole(employee.getRole());

    return employeeRepository.save(existingEmployee);
}

public void deleteEmployee(Integer id) {
    employeeRepository.deleteById(id);
}
}