package org.example.demo1.model;

import java.time.LocalDate;

public class Employee {
    int id;
    String fullName;
    String email;
    String phone;
    String department;
    double salary;
    LocalDate hireDate;

    // Constructors
    public Employee() {
    }

    public Employee(String fullName, String email, String phone, String department, double salary, LocalDate hireDate) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public LocalDate getHireDate() { return hireDate; }
}
