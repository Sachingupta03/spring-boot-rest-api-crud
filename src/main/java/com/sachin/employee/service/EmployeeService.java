package com.sachin.employee.service;

import com.sachin.employee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Integer id);

    boolean deleteEmployeeById(Integer id);

    Page<Employee> getEmployeeWithPagination(int page, int size);

    Employee updateEmployee(Integer id, Employee employee);
}