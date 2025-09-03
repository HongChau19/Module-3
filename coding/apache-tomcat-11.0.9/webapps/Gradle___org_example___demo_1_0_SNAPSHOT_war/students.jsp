<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh sách sinh viên</title>
</head>
<body>
    <h2>Danh sách sinh viên</h2>
    <a href="./addStudent.jsp">Thêm sinh viên</a>

    <form action="./students" method="get">
        <select name="gpa">
            <option value="">Tất cả</option>
            <option value="Giỏi">G</option>
            <option value="Khá">Kh</option>
            <option value="Trung bình">T</option>
            <option value="Yếu">Y</option>
        </select>
        <button type="submit">Lọc</button>
    </form>
    <table border="1">
        <tr>
            <th>Mã số</th>
            <th>Họ tên</th>
            <th>Điểm tổng kết</th>
        </tr>
        <c:forEach items="${studentList}" var="students">
        <tr>
           <td>${students.studentId}</td>
           <td>${students.fullName}</td>
           <td>${students.gpa}</td>
        </tr>
        </table>
        </c:forEach>
</div>
</body>
</html>