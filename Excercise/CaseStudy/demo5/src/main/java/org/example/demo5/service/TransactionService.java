package org.example.demo5.service;

import org.example.demo5.model.Transaction;

import java.sql.*;
import java.util.*;

public class TransactionService {

    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "Insert into transactions (description, amount, type, date) values (?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, transaction.getDescription());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getType()); // Cập nhật để sử dụng cột 'type'
            preparedStatement.setDate(4, transaction.getDate());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteTransaction(int id) throws SQLException {
        String sql = "Delete from transactions where id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        // Thay đổi câu lệnh SQL, không cần JOIN
        String sql = "SELECT * FROM transactions ORDER BY date DESC";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setDescription(resultSet.getString("description"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setType(resultSet.getString("type")); // Cập nhật để lấy dữ liệu từ cột 'type'
                transaction.setDate(resultSet.getDate("date"));
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    // Phương thức này không còn cần thiết và đã được xóa bỏ
    // public Map<Integer, String> getAllTransactionTypes() throws SQLException { ... }
}
