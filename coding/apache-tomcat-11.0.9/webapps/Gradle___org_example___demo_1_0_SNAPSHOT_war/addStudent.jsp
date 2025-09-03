<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Thêm sinh viên</title>
</head>
<body>
    <h2>Thêm sinh viên mới</h2>
    <form action="./add-Student" method="POST">
            <label for="studentId">Mã số</label>
            <input type="text" id="studentId" name="studentId"/><br/>
            <label for="fullName">Họ và tên</label>
            <input type="text" id="fullName" name="fullName"/><br/>
            <label for="gpa">Điểm tổng kết</label>
            <input type="text" id="gpa" name="gpa"/><br/>
            <button type="submit">Thêm sinh viên</button>
    </form>
</body>
</html>