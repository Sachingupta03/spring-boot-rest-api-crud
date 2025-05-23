package com.sachin.employee.controller;


import com.sachin.employee.model.Employee;
import com.sachin.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
    // Delete employee by ID
    @DeleteMapping("/removeId/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        boolean deleted = employeeService.deleteEmployeeById (id);
        if (deleted) {
            return ResponseEntity.ok ("Employee deleted successfully with ID: " + id);
        } else {
            return ResponseEntity.status (404).body ("Employee not found with ID: " + id);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOpt = employeeService.getEmployeeById(id);

        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();

            //  Update actual fields from your model
            existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setPassword(updatedEmployee.getPassword());

            Employee savedEmployee = employeeService.addEmployee(existingEmployee);
            return ResponseEntity.ok(savedEmployee);
        } else {
            return ResponseEntity.notFound().build();  // clean 404 response
        }
    }

}
