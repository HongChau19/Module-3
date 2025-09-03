<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action = "" method="get">
    <input type="text" name="q" placeholder="Tìm kiếm theo tên hoặc email">
    <button type="submit">Tìm kiếm</button>
</form>

<table>
<tr>
    <th>ID</th>
    <th>Họ Tên</th>
    <th>Email</th>
    <th>Phòng Ban</th>
    <th>Lương</th>
    <th>Ngày vào làm</th>
    <th>Thao tác</th>
</tr>
<c:forEach items="${employees}" var="employee">
    <tr>
        <td>${employee.id}</td>
        <td>${employee.fullname}</td>
        <td>${employee.email}</td>
        <td>${employee.department}</td>
        <td>${employee.salary}</td>
        <td>${employee.hireDate}</td>
        <td class="actions">
            <a href="employees?action=update&id=${employee.id}">Sửa</a>
            <a href="employees?action=delete&id=${employee.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa nhân viên này?')">Xóa</a>
        </td>
    </tr>
</c:forEach>
</table>

</body>
</html>
