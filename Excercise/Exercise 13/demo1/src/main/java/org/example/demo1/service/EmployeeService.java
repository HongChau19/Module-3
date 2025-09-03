package org.example.demo1.service;

import org.example.demo1.model.Employee;
import java.util.List;

public interface EmployeeService {
    void add(Employee employee);
    void update(Employee employee);
    void delete(int id);
    Employee findById(int id);

    List<Employee> fillAll();
}
