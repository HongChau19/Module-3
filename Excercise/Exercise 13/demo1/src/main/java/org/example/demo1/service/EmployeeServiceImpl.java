package org.example.demo1.service;

import org.example.demo1.model.Employee;

import java.sql.*;
import java.time.LocalDate;

public abstract class EmployeeServiceImpl implements EmployeeService {
    DBConnection connection = new DBConnection();

    @Override
    public void add(Employee employee) {
    String sql = "insert into employee (full_name, email, phone, department, slary, hireDate) value(?,?,?,?,?,?)";
    try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
        preparedStatement.setString(1, employee.getFullName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setString(3, employee.getPhone());
        preparedStatement.setString(4, employee.getDepartment());
        preparedStatement.setDouble(5,employee.getSalary());
        preparedStatement.setDate(6, Date.valueOf(employee.getHireDate()));

        preparedStatement.executeUpdate();

    }catch (SQLException e){
        throw new RuntimeException("Lỗi câu lệnh SQL");
    }

}

    @Override
    public void update(Employee employee) {
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT * FROM employee WHERE id=?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setFullName(resultSet.getString("full_name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setHireDate(LocalDate.parse(resultSet.getDate("hire_date")));

            }

        }catch (SQLException e){
            throw new RuntimeException("Lỗi câu lệnh SQL");
        }
        return null;
    }
}