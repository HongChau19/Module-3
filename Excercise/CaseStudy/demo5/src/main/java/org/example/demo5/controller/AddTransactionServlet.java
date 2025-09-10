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

@WebServlet("/add")
public class AddTransactionServlet extends HttpServlet {
    private TransactionService transactionService;

    public void init() {
        transactionService = new TransactionService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String categories = request.getParameter("categories");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String type = request.getParameter("type");
        String dateString = request.getParameter("date");
        Date date = Date.valueOf(dateString);
        String description = request.getParameter("description");
        String confirmAdd = request.getParameter("confirm_add");

        try {
            double currentBalance = transactionService.getTotalBalance();
            if ("Chi".equals(type) && amount > currentBalance && !"true".equals(confirmAdd)) {
                String warningMessage = "CẢNH BÁO: Số tiền chi vượt quá số dư hiện có!";
                request.getSession().setAttribute("warningMessage", warningMessage);

                request.getSession().setAttribute("pendingTransaction", new Transaction(categories, amount, type, date, description));

                response.sendRedirect(request.getContextPath() + "/list");
                return; // Dừng lại, không thêm giao dịch
            }

            transactionService.addTransaction(new Transaction(categories, amount, type, date, description));

            request.getSession().removeAttribute("pendingTransaction");
            response.sendRedirect(request.getContextPath() + "/list");

        } catch (SQLException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dữ liệu lỗi");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lỗi");
        }
    }
}