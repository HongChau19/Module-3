package org.example.demo4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        int typeId = Integer.parseInt(request.getParameter("type"));
        String dateString = request.getParameter("date");
        Date date = Date.valueOf(dateString);

        try {
            transactionService.addTransaction(new Transaction(description, amount, typeId, date));
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
