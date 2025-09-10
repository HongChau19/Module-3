package org.example.demo5.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo5.model.Transaction;
import org.example.demo5.service.TransactionService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditTransactionServlet extends HttpServlet {
    private TransactionService transactionService;
    public void init() {
        transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Transaction existingTransaction = transactionService.getTransactionById(id);
            request.setAttribute("transaction", existingTransaction);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dữ liệu lỗi.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ hoặc bị thiếu.");
            return;
        }

        String categories = request.getParameter("categories");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String type = request.getParameter("type");
        String dateString = request.getParameter("date");
        Date date = Date.valueOf(dateString);
        String description = request.getParameter("description");

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setCategories(categories);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setDate(date);
        transaction.setDescription(description);

        try {
            transactionService.updateTransaction(transaction);
            response.sendRedirect(request.getContextPath() + "/list");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi cơ sở dữ liệu khi cập nhật.");
        }
    }
}
