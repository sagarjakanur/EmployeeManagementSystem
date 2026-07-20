package com.sagar.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.sagar.backend.dto.EmployeeRequest;
import com.sagar.backend.dto.EmployeeResponse;
import com.sagar.backend.entity.Department;
import com.sagar.backend.entity.Employee;
import com.sagar.backend.entity.Role;
import com.sagar.backend.exception.EmployeeNotFoundException;
import com.sagar.backend.repository.DepartmentRepository;
import com.sagar.backend.repository.EmployeeRepository;
import com.sagar.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           RoleRepository roleRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

     
// GET ALL WITH PAGINATION & SORTING
public Page<Employee> getAllEmployees(
        int page,
        int size,
        String sortBy,
        String direction) {

    logger.info(
            "Fetching employees - Page: {}, Size: {}, Sort By: {}, Direction: {}",
            page, size, sortBy, direction);

    Sort sort;

    if (direction.equalsIgnoreCase("desc")) {
        sort = Sort.by(sortBy).descending();
    } else {
        sort = Sort.by(sortBy).ascending();
    }

    Pageable pageable = PageRequest.of(page, size, sort);

    return employeeRepository.findAll(pageable);
}

    // POST USING DTO
    public EmployeeResponse saveEmployee(EmployeeRequest request) {

        logger.info("Creating a new employee: {} {}", 
                request.getFirstName(), request.getLastName());

        Department department =
                departmentRepository.findById(request.getDepartmentId()).orElse(null);

        Role role =
                roleRepository.findById(request.getRoleId()).orElse(null);

        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());

        employee.setDepartment(department);
        employee.setRole(role);

        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Employee created successfully with id: {}",
                savedEmployee.getEmployeeId());

        EmployeeResponse response = new EmployeeResponse();

        response.setEmployeeId(savedEmployee.getEmployeeId());
        response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setEmail(savedEmployee.getEmail());
        response.setPhone(savedEmployee.getPhone());
        response.setSalary(savedEmployee.getSalary());
        response.setHireDate(savedEmployee.getHireDate());

        response.setDepartmentName(savedEmployee.getDepartment().getDepartmentName());
        response.setRoleName(savedEmployee.getRole().getRoleName());

        return response;
    }

    // GET BY ID
    public Employee getEmployeeById(Integer id) {

    logger.info("Fetching employee with id: {}", id);

    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Employee not found with id: {}", id);
                return new EmployeeNotFoundException(
                        "Employee not found with id " + id);
            });

    logger.info("Employee found with id: {}", id);

    return employee;
}

    // GET DTO
    public EmployeeResponse getEmployeeResponseById(Integer id) {

        logger.info("Fetching employee DTO with id: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id " + id));

        EmployeeResponse response = new EmployeeResponse();

        response.setEmployeeId(employee.getEmployeeId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setSalary(employee.getSalary());
        response.setHireDate(employee.getHireDate());

        response.setDepartmentName(employee.getDepartment().getDepartmentName());
        response.setRoleName(employee.getRole().getRoleName());

        return response;
    }

    // UPDATE USING DTO
    public EmployeeResponse updateEmployee(Integer id, EmployeeRequest request) {

        logger.info("Updating employee with id: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id " + id));

        Department department =
                departmentRepository.findById(request.getDepartmentId()).orElse(null);

        Role role =
                roleRepository.findById(request.getRoleId()).orElse(null);

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());

        employee.setDepartment(department);
        employee.setRole(role);

        Employee updatedEmployee = employeeRepository.save(employee);

        logger.info("Employee updated successfully with id: {}",
                updatedEmployee.getEmployeeId());

        EmployeeResponse response = new EmployeeResponse();

        response.setEmployeeId(updatedEmployee.getEmployeeId());
        response.setFirstName(updatedEmployee.getFirstName());
        response.setLastName(updatedEmployee.getLastName());
        response.setEmail(updatedEmployee.getEmail());
        response.setPhone(updatedEmployee.getPhone());
        response.setSalary(updatedEmployee.getSalary());
        response.setHireDate(updatedEmployee.getHireDate());

        response.setDepartmentName(updatedEmployee.getDepartment().getDepartmentName());
        response.setRoleName(updatedEmployee.getRole().getRoleName());

        return response;
    }




// SEARCH BY FIRST NAME
public List<Employee> getEmployeesByFirstName(String firstName) {

    logger.info("Searching employees with first name: {}", firstName);

    return employeeRepository.findByFirstName(firstName);
}


// SEARCH BY LAST NAME
public List<Employee> getEmployeesByLastName(String lastName) {

    logger.info("Searching employees with last name: {}", lastName);

    return employeeRepository.findByLastName(lastName);
}





// SEARCH BY EMAIL
public List<Employee> getEmployeesByEmail(String email) {

    logger.info("Searching employee with email: {}", email);

    return employeeRepository.findByEmail(email);
}

// SEARCH BY DEPARTMENT
public List<Employee> getEmployeesByDepartment(String departmentName) {

    logger.info("Searching employees in department: {}", departmentName);

    return employeeRepository.findByDepartmentDepartmentName(departmentName);
}

// SEARCH BY ROLE
public List<Employee> getEmployeesByRole(String roleName) {

    logger.info("Searching employees with role: {}", roleName);

    return employeeRepository.findByRoleRoleName(roleName);
}

// SEARCH BY SALARY RANGE
public List<Employee> getEmployeesBySalaryRange(
        BigDecimal minSalary,
        BigDecimal maxSalary) {

    logger.info("Searching employees with salary between {} and {}",
            minSalary, maxSalary);

    return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
}

// PARTIAL SEARCH
public List<Employee> searchEmployeeByFirstName(String firstName) {

    logger.info("Searching employees containing: {}", firstName);

    return employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
}

    // DELETE
    public void deleteEmployee(Integer id) {

        logger.info("Deleting employee with id: {}", id);

        employeeRepository.deleteById(id);

        logger.info("Employee deleted successfully with id: {}", id);
    }
}