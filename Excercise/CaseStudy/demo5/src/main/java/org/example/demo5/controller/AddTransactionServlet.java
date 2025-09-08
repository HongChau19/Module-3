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
        String description = request.getParameter("description");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String type = request.getParameter("type"); // Lấy giá trị chuỗi từ form
        String dateString = request.getParameter("date");
        Date date = Date.valueOf(dateString);

        try {
            transactionService.addTransaction(new Transaction(description, amount, type, date)); // Truyền giá trị chuỗi
            response.sendRedirect(request.getContextPath() + "/list");
        } catch (SQLException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dữ liệu lỗi");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sai định dạng dữ liệu");
        }
    }
}
