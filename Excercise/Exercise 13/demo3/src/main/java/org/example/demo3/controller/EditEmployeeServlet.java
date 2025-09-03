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

@WebServlet("/edit-employee")
public class EditEmployeeServlet extends HttpServlet {
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
            int employeeId = Integer.parseInt(request.getParameter("id"));
            Employee existingEmployee = employeeService.getEmployeeById(employeeId);
            List<Department> listDepartments = departmentService.getAllDepartments();

            request.setAttribute("employee", existingEmployee);
            request.setAttribute("listDepartments", listDepartments);
            request.getRequestDispatcher("/WEB-INF/form-employee.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            BigDecimal salary = new BigDecimal(request.getParameter("salary"));
            Date hireDate = Date.valueOf(request.getParameter("hireDate"));
            String departmentIdStr = request.getParameter("departmentId");
            Integer departmentId = null;
            if (departmentIdStr != null && !departmentIdStr.isEmpty()) {
                departmentId = Integer.parseInt(departmentIdStr);
            }

            Employee updatedEmployee = new Employee();
            updatedEmployee.setId(id);
            updatedEmployee.setFullName(fullName);
            updatedEmployee.setEmail(email);
            updatedEmployee.setSalary(salary);
            updatedEmployee.setHireDate(hireDate);
            updatedEmployee.setDepartmentId(departmentId);

            employeeService.updateEmployee(updatedEmployee);

            response.sendRedirect("list-employees");
        } catch (SQLException ex) {
            request.setAttribute("error", "Có lỗi xảy ra khi cập nhật nhân viên. Vui lòng thử lại.");
            doGet(request, response);
        }
    }
}
