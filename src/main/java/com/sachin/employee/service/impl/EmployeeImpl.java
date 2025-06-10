package com.sachin.employee.service.impl;

import com.sachin.employee.model.Employee;
import com.sachin.employee.repository.EmployeeRepository;
import com.sachin.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        logger.info("Adding new employee: {}", employee.getEmployeeName());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> getEmployeeWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee updatedEmployee) {
        logger.info ("Updating employee with ID: {}", id);
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById (id);

        Employee savedEmployee = null;
        if (existingEmployeeOpt.isPresent ()) {
            Employee existingEmployee = existingEmployeeOpt.get ();

            existingEmployee.setEmployeeName (updatedEmployee.getEmployeeName ());
            existingEmployee.setEmail (updatedEmployee.getEmail ());
            existingEmployee.setPassword (updatedEmployee.getPassword ());

            savedEmployee = employeeRepository.save (existingEmployee);

        }
        return savedEmployee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        logger.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id);
    }

    @Override
    public boolean deleteEmployeeById(Integer id) {
        logger.info("Attempting to delete employee with ID: {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            logger.info("Employee deleted with ID: {}", id);
            return true;
        } else {
            logger.warn("Employee not found with ID: {}", id);
            return false;
        }
    }
}
