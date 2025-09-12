package org.example.test.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.test.service.MatBangService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteMatBangServlet extends HttpServlet {
    private MatBangService matBangService;

    public void init() {
        matBangService = new MatBangService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String maMatBang = request.getParameter("maMatBang");
            matBangService.deleteMatBang(maMatBang);
            response.sendRedirect("list");
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}