package org.example.demo3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo3.Service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-employee")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int employeeId = Integer.parseInt(request.getParameter("id"));
            employeeService.deleteEmployee(employeeId);
            response.sendRedirect("list-employees");
        } catch (SQLException ex) {
            request.setAttribute("error", "Không thể xóa nhân viên này. Vui lòng thử lại.");
            request.getRequestDispatcher("/list-employees").forward(request, response);
        }
    }

    // Không cần doPost cho chức năng xóa
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
