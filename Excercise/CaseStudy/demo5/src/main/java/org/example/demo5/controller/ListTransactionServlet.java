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
import java.util.Map;

@WebServlet("/list")
public class ListTransactionServlet extends HttpServlet {
    private TransactionService transactionService;

    public void init() {
        this.transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Transaction> transactions;
            String typeFilter = request.getParameter("type");

            if (typeFilter == null || typeFilter.isEmpty() || "all".equals(typeFilter)) {
                transactions = transactionService.getAllTransactions();
                request.setAttribute("currentFilter", "all");
            } else {
                transactions = transactionService.getTransactionsByType(typeFilter);
                request.setAttribute("currentFilter", typeFilter);
            }

            request.setAttribute("transactions", transactions);


            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dữ liệu lỗi.");
        }
    }
}
