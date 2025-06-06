package com.sachin.employee.model;

import jakarta.persistence.*;
@Entity
@Table(name = "employee")
public class Employee {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int employeeId;

        @Column(name = "employee_name", length = 255)
        private String employeeName;

        @Column(name = "email", length = 255)
        private String email;

        @Column(name = "password", length = 255)
        private String password;

        // No-arg constructor
        public Employee() {
        }

        // Constructor without employeeId (usually autogenerated)
        public Employee(String employeeName, String email, String password) {
            this.employeeName = employeeName;
            this.email = email;
            this.password = password;
        }

        // Getters and setters
        public int getEmployeeId() {
            return employeeId;
        }

        // Usually, you don't set employeeId manually because it's autogenerated,
        // but setter is here if needed.
        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


