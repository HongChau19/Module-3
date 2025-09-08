package org.example.demo4.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo4.service.TransactionService;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/delete")
public class DeleteTransactionServlet extends HttpServlet {
    private TransactionService transactionService;

    public void init(){
        transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            transactionService.deleteTransaction(id);
            response.sendRedirect(request.getContextPath()+"/list");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dữ liệu lỗi");
        }
    }
}
