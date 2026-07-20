package com.sagar.backend.service;

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

    // GET ALL
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // POST USING DTO
    public EmployeeResponse saveEmployee(EmployeeRequest request) {

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

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id " + id));
    }

    // GET DTO
    public EmployeeResponse getEmployeeResponseById(Integer id) {

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

    // DELETE
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}