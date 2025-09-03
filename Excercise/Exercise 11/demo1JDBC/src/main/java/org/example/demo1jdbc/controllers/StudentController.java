package org.example.demo1jdbc.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo1jdbc.models.Student;
import org.example.demo1jdbc.services.StudentDBService;
import org.example.demo1jdbc.services.StudentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet ("/students")
public class StudentController extends HttpServlet {
    StudentService studentService = new StudentDBService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.findAll();
        req.setAttribute("students", students); // đưa vào bộ nhớ

        RequestDispatcher dispatcher = req.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(req, resp);
    }

    //Nhận dữ liệu từ form (student-add.jsp)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("masv");
        String name = req.getParameter("hoten");
        String subject = req.getParameter("chuyennganh");

        //Tạo đối tượng
        Student student = new Student(id, name, subject);

        //Lưu vào CSDL
        studentService.save(student);

        resp.sendRedirect("/students");

        super.doPost(req, resp);
    }
}
