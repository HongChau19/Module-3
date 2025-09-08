package org.example.demo4.service;

import org.example.demo4.model.Transaction;

import java.sql.*;
import java.util.*;

public class TransactionService {

    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "Insert into transactions (description, amount, type_id, date) values (?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setString(1, transaction.getDescription());
             preparedStatement.setDouble(2, transaction.getAmount());
             preparedStatement.setInt(3, transaction.getTypeId());
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
        String sql = "SELECT t.*, tt.name AS type_name FROM transactions t " +
                    "JOIN transaction_types tt ON t.type_id = tt.id " +
                    "ORDER BY t.date DESC";;
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setDescription(resultSet.getString("description"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTypeId(resultSet.getInt("type_id"));
                transaction.setTypeName(resultSet.getString("type_name"));
                transaction.setDate(resultSet.getDate("date"));
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public Map<Integer, String> getAllTransactionTypes() throws SQLException {
        Map<Integer, String> transactionTypes = new LinkedHashMap<>();
        String sql = "SELECT id, name FROM transaction_types";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                transactionTypes.put(id, name);
            }
        }
        return transactionTypes;
    }
}
