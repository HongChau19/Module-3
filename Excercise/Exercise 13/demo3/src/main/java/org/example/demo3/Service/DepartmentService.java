package org.example.demo3.Service;

import org.example.demo3.model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

    public void addDepartment(Department department) throws SQLException, IllegalArgumentException {
        if (isDepartmentNameExists(department.getName(), 0)) {
            throw new IllegalArgumentException("Tên phòng ban đã tồn tại.");
        }
        String sql = "INSERT INTO department (name, description) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                department.setDescription(resultSet.getString("description"));
                departments.add(department);
            }
        }
        return departments;
    }

    public Department getDepartmentById(int id) throws SQLException {
        String sql = "SELECT * FROM department WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Department(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    );
                }
            }
        }
        return null;
    }

    public void updateDepartment(Department department) throws SQLException, IllegalArgumentException {
        if (isDepartmentNameExists(department.getName(), department.getId())) {
            throw new IllegalArgumentException("Tên phòng ban đã tồn tại.");
        }
        String sql = "UPDATE department SET name=?, description=? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getDescription());
            preparedStatement.setInt(3, department.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteDepartment(int id) throws SQLException {
        String sql = "DELETE FROM department WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public boolean isDepartmentNameExists(String name, int idToExclude) throws SQLException {
        String sql = "SELECT COUNT(*) FROM department WHERE name = ? AND id != ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, idToExclude);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
