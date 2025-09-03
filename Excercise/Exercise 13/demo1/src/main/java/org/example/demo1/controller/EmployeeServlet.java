package org.example.demo1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo1.model.Employee;
import org.example.demo1.service.EmployeeService;
import org.example.demo1.service.EmployeeServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;
//    @Override
//    public void init() throws ServletException {
//        employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
        if (action == null) action ="list";
        try {
            switch (action) {
                case "list":
                    break;
                case "add":
                    //Hiển thị form khi user request
                    req.getRequestDispatcher("/WEB-INF/views/employee-form.jsp").forward(req, resp);
                    break;
                case "update":
                    int id = Integer.parseInt(req.getParameter("id"));
                    Employee employee = employeeService.findById(id);

                    //Hiển thị form khi request
                    req.getRequestDispatcher("/WEB-INF/views/employee-form.jsp").forward(req, resp);
                    break;
                case "delete":
                    break;
                default:
                req.setAttribute("message", "Thao tác không tồn tại");
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                break;
            }
        } catch (Exception e) {
        throw new ServletException(e);
}
}

    private void ListEmployee(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Nhận dữ liệu
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String department = req.getParameter("department");
        //Tất cả đều quy về string(qua HTTP)
        double salary = Double.parseDouble(req.getParameter("salary"));
        LocalDate hireDate = LocalDate.parse(req.getParameter("hireDate"));

        List<String> errors = new ArrayList<String>();

        //Kiểm tra
        if (fullName == null || fullName.equals("")) {errors.add("Họ tên không được để trống");}
        if (email == null || email.equals("")) {errors.add("Họ tên không được để trống");}

        //Gọi hàm thêm

        Employee employee = new Employee(fullName, email, phone, department, salary, hireDate);
        employeeService.add(employee);

        //Nếu có lỗi
            if (errors.size() > 0) {
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/views/employees.jsp").forward(req, resp);
        } else {
                resp.sendRedirect(req.getContextPath()+"/employees?action=list");
            }
    }
}
