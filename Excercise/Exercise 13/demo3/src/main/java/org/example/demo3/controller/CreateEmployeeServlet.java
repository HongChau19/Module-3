package org.example.demo3.controller;

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
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/create-employee")
public class CreateEmployeeServlet extends HttpServlet {
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
            List<Department> listDepartments = departmentService.getAllDepartments();
            request.setAttribute("listDepartments", listDepartments);
            request.getRequestDispatcher("/WEB-INF/form-employee.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            BigDecimal salary = new BigDecimal(request.getParameter("salary"));
            Date hireDate = Date.valueOf(request.getParameter("hireDate"));
            String departmentIdStr = request.getParameter("departmentId");
            Integer departmentId = null;
            if (departmentIdStr != null && !departmentIdStr.isEmpty()) {
                departmentId = Integer.parseInt(departmentIdStr);
            }

            // Tạo đối tượng Employee và thêm vào DB
            Employee newEmployee = new Employee();
            newEmployee.setFullName(fullName);
            newEmployee.setEmail(email);
            newEmployee.setSalary(salary);
            newEmployee.setHireDate(hireDate);
            newEmployee.setDepartmentId(departmentId);

            employeeService.addEmployee(newEmployee);

            // Chuyển hướng về trang danh sách nhân viên
            response.sendRedirect("list-employees");
        } catch (SQLException ex) {
            request.setAttribute("error", "Có lỗi xảy ra khi thêm nhân viên. Vui lòng thử lại.");
            doGet(request, response); // Quay lại trang form để người dùng sửa
        }
    }

}
