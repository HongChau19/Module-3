<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sinh viên</title>
</head>
<body>
<form action="./students" method="POST">
    Mã sinh viên<br/>
    <input type="text" name="masv"/><br/>
    Họ và tên<br/>
    <input type="text" name="hoten"/><br/>
    Chuyên ngành<br/>
    <input type="text" name="chuyennganh"/><br/>

    <button type="submit">Thêm sinh viên</button>
</form>
</body>
</html>