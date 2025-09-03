package org.example.demo3.Service;

import org.example.demo3.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

        public void addEmployee(Employee employee) throws SQLException {
            String sql = "INSERT INTO employee (name, email, salary, hire_date, department_id) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, employee.getFullName());
                preparedStatement.setString(2, employee.getEmail());
                preparedStatement.setBigDecimal(3, employee.getSalary());
                preparedStatement.setDate(4, employee.getHireDate());
                if (employee.getDepartmentId() != null) {
                    preparedStatement.setInt(5, employee.getDepartmentId());
                } else {
                    preparedStatement.setNull(5, Types.INTEGER);
                }
                preparedStatement.executeUpdate();
            }
        }

        public List<Employee> getAllEmployees(Integer departmentId) throws SQLException {
            List<Employee> employees = new ArrayList<>();
            StringBuilder sqlBuilder = new StringBuilder("SELECT e.*, d.name AS department_name FROM employee e LEFT JOIN department d ON e.department_id = d.id");

            if (departmentId != null && departmentId > 0) {
                sqlBuilder.append(" WHERE e.department_id = ?");
            }

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString())) {
                if (departmentId != null && departmentId > 0) {
                    preparedStatement.setInt(1, departmentId);
                }
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Employee employee = new Employee();
                        employee.setId(resultSet.getInt("id"));
                        employee.setFullName(resultSet.getString("name"));
                        employee.setEmail(resultSet.getString("email"));
                        employee.setSalary(resultSet.getBigDecimal("salary"));
                        employee.setHireDate(resultSet.getDate("hire_date"));

                        int deptId = resultSet.getInt("department_id");
                        if (!resultSet.wasNull()) {
                            employee.setDepartmentId(deptId);
                        }
                        employee.setDepartmentName(resultSet.getString("department_name"));
                        employees.add(employee);
                    }
                }
            }
            return employees;
        }

        public Employee getEmployeeById(int id) throws SQLException {
            String sql = "SELECT e.*, d.name AS department_name FROM employee e LEFT JOIN department d ON e.department_id = d.id WHERE e.id = ?";
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Employee employee = new Employee();
                        employee.setId(resultSet.getInt("id"));
                        employee.setFullName(resultSet.getString("name"));
                        employee.setEmail(resultSet.getString("email"));
                        employee.setSalary(resultSet.getBigDecimal("salary"));
                        employee.setHireDate(resultSet.getDate("hire_date"));
                        int deptId = resultSet.getInt("department_id");
                        if (!resultSet.wasNull()) {
                            employee.setDepartmentId(deptId);
                        }
                        employee.setDepartmentName(resultSet.getString("department_name"));
                        return employee;
                    }
                }
            }
            return null;
        }

        public void updateEmployee(Employee employee) throws SQLException {
            String sql = "UPDATE employee SET name=?, email=?, salary=?, hire_date=?, department_id=? WHERE id=?";
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, employee.getFullName());
                preparedStatement.setString(2, employee.getEmail());
                preparedStatement.setBigDecimal(3, employee.getSalary());
                preparedStatement.setDate(4, employee.getHireDate());
                if (employee.getDepartmentId() != null) {
                    preparedStatement.setInt(5, employee.getDepartmentId());
                } else {
                    preparedStatement.setNull(5, Types.INTEGER);
                }
                preparedStatement.setInt(6, employee.getId());
                preparedStatement.executeUpdate();
            }
        }

        public void deleteEmployee(int id) throws SQLException {
            String sql = "DELETE FROM employee WHERE id=?";
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        }

        public int countEmployeesByDepartmentId(int departmentId) throws SQLException {
            String sql = "SELECT COUNT(*) FROM employee WHERE department_id = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, departmentId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return 0;
        }
}