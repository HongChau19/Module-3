package org.example.demo5.service;

import org.example.demo5.model.Transaction;

import java.sql.*;
import java.util.*;

public class TransactionService {

    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "Insert into transactions (categories, amount, type, date, description) values (?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, transaction.getCategories());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getType());
            preparedStatement.setDate(4, transaction.getDate());
            preparedStatement.setString(5, transaction.getDescription());

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

    public void updateTransaction(Transaction transaction) throws SQLException {
        String sql = "UPDATE transactions SET categories = ?, amount = ?, type = ?, date = ?, description = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, transaction.getCategories());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getType());
            preparedStatement.setDate(4, transaction.getDate());
            preparedStatement.setString(5, transaction.getDescription());
            preparedStatement.setInt(6, transaction.getId());
            preparedStatement.executeUpdate();
        }
    }

    public Transaction getTransactionById(int id) throws SQLException {
        Transaction transaction = null;
        String sql = "SELECT * FROM transactions WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    transaction = new Transaction();
                    transaction.setId(resultSet.getInt("id"));
                    transaction.setCategories(resultSet.getString("categories"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transaction.setType(resultSet.getString("type"));
                    transaction.setDate(resultSet.getDate("date"));
                    transaction.setDescription(resultSet.getString("description"));
                }
            }
        }
        return transaction;
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY date DESC";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setCategories(resultSet.getString("categories"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setType(resultSet.getString("type"));
                transaction.setDate(resultSet.getDate("date"));
                transaction.setDescription(resultSet.getString("description"));
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public List<Transaction> getTransactionsByType(String type) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE type = ? ORDER BY date DESC";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, type);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setId(resultSet.getInt("id"));
                    transaction.setCategories(resultSet.getString("categories"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transaction.setType(resultSet.getString("type"));
                    transaction.setDate(resultSet.getDate("date"));
                    transaction.setDescription(resultSet.getString("description"));
                    transactionList.add(transaction);
                }
            }
        }
        return transactionList;
    }

        public double getTotalBalance() throws SQLException {
            String sql = "SELECT SUM(CASE WHEN type = 'Thu' THEN amount ELSE -amount END) AS totalBalance FROM transactions";
            try (Connection connection = DBConnection.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    return resultSet.getDouble("totalBalance");
                }
            }
            return 0;
        }
    }
