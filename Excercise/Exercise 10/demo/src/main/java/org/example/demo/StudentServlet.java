package org.example.demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lấy dữ liệu trong request
        String classification= request.getParameter("classification");
        List<Student> filteredStudents = new ArrayList<>();
        if (classification != null && !classification.isEmpty() && !classification.equals("Tất cả")) {
            for (Student student : studentList) {
                if (student.getClassification().equals(classification)) {
                    filteredStudents.add(student);
                }
            }
        } else {
            filteredStudents = studentList;
        }

//        //Danh sách sinh viên - sau này có thể nhúng câu lệnh SQL vào để truy vấn dữ liệu từ MySQL
//        List<Student> studentList = new ArrayList<Student>();
//        //Thêm sinh viên
//           studentList.add(new Student("SV001", "Phạm Thị D", 7.2f));
//           studentList.add(new Student("SV002", "Trần Thị B", 6.8f));
//           studentList.add(new Student("SV003", "Lê Văn C", 4.5f));
//           studentList.add(new Student("SV004", "Phạm Thị D", 7.2f));
        //context (bộ nhớ)
        //Application context: tất cả servlet + jsp
        //Request context: servlet/jsp liên quan
        ServletContext servletContext = getServletContext(); //nằm ở GenericServlet
        List<Student> studentList = (List<Student>) servletContext.getAttribute("students");
        if (studentList == null) {
            studentList = new ArrayList<>();
            servletContext.setAttribute("students", studentList);
        }
        //Lọc danh sách theo phân loại rồi mới gán vào bộ nhớ
        //Chuyển dữ liệu từ bộ nhớ sang JSP
        request.setAttribute("studentList", filteredStudents);
        request.setAttribute("selectedClassification", classification);

        // Gọi JSP --> trả về HTML (JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
        dispatcher.forward(request, response);
    }
}