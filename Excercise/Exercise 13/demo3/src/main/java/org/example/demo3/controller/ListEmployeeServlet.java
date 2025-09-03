package org.example.demo3.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo3.Service.DepartmentService;
import org.example.demo3.Service.EmployeeService;
import org.example.demo3.model.Department;
import org.example.demo3.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/department")
public class ListEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public void init() {
        employeeService = new EmployeeService();
        departmentService = new DepartmentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số departmentId từ request để lọc
            String departmentIdStr = request.getParameter("departmentId");
            Integer departmentId = null;
            if (departmentIdStr != null && !departmentIdStr.isEmpty()) {
                departmentId = Integer.parseInt(departmentIdStr);
            }

            // Lấy danh sách nhân viên và phòng ban
            List<Employee> listEmployees = employeeService.getAllEmployees(departmentId);
            List<Department> listDepartments = departmentService.getAllDepartments();

            // Đặt các thuộc tính để gửi tới JSP
            request.setAttribute("listEmployees", listEmployees);
            request.setAttribute("listDepartments", listDepartments);
            request.setAttribute("selectedDepartmentId", departmentId); // Giữ lại giá trị đã chọn

            // Chuyển tiếp yêu cầu đến trang JSP
            request.getRequestDispatcher("/views/employees/list-employees.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Không cần doPost cho Servlet này vì nó chỉ có chức năng hiển thị
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

