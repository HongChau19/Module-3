package org.example.demo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/add-Student")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String studentId = request.getParameter("studentId");
        String fullName = request.getParameter("fullName");
        float gpa = Float.parseFloat(request.getParameter("gpa"));

        // Tạo đối tượng Student mới
        Student newStudent = new Student(studentId, fullName, gpa);

        //lấy dữ liệu từ bộ nhớ
        ServletContext servletContext = getServletContext(); //nằm ở GenericServlet
        List<Student> studentList = (List<Student>) servletContext.getAttribute("student");
        if (studentList == null) {
            studentList = new ArrayList<>();
            servletContext.setAttribute("students", studentList);
        }
        //đưa vào bộ nhớ chung (danh sách sinh viên)
        studentList.add(newStudent);

        // Chuyển hướng người dùng về trang danh sách sinh viên
        response.sendRedirect("./students");
    }
}