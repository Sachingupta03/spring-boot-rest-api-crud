package com.sachin.employee.controller;

import com.sachin.employee.model.Employee;
import com.sachin.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Fetching all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        logger.info("Saving employee: {}", employee);
        Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Integer id) {
        logger.info("Fetching employee with ID: {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/removeId/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        logger.warn("Deleting employee with ID: {}", id);
        boolean deleted = employeeService.deleteEmployeeById(id);
        if (deleted) {
            logger.info("Deleted employee with ID: {}", id);
            return ResponseEntity.ok("Employee deleted successfully with ID: " + id);
        } else {
            logger.error("Failed to delete: Employee not found with ID: {}", id);
            return ResponseEntity.status(404).body("Employee not found with ID: " + id);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        logger.info("Updating employee with ID: {}", id);
        Optional<Employee> existingEmployeeOpt = employeeService.getEmployeeById(id);

        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();

            existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setPassword(updatedEmployee.getPassword());

            Employee savedEmployee = employeeService.addEmployee(existingEmployee);
            logger.info("Updated employee: {}", savedEmployee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            logger.warn("Employee with ID: {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
    }
}
