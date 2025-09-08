package org.example.demo5.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo5.model.Transaction;
import org.example.demo5.service.TransactionService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list")
public class ListTransactionServlet extends HttpServlet {
    private TransactionService transactionService;

    public void init() {
        this.transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            // Đã xóa dòng lấy transactionTypes

            request.setAttribute("transactions", transactions);
            // Đã xóa dòng setAttribute cho transactionTypes

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }
}
