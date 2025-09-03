<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="./addemployee.jsp">Thêm nhân viên</a>

<form action = "" method="get">
    <input type="text" name="q" placeholder="Tìm kiếm theo tên hoặc email">
    <button type="submit">Tìm kiếm</button>
</form>

<form class="sort-form" action="employees" method="get">
    <select name="sortBy" id="sortBy">
        <option value="full_name" <c:if test="${param.sortBy == 'full_name'}">selected</c:if>>Họ Tên</option>
        <option value="email" <c:if test="${param.sortBy == 'email'}">selected</c:if>>Email</option>
        <option value="department" <c:if test="${param.sortBy == 'department'}">selected</c:if>>Phòng Ban</option>
        <option value="salary" <c:if test="${param.sortBy == 'salary'}">selected</c:if>>Lương</option>
        <option value="hire_date" <c:if test="${param.sortBy == 'hire_date'}">selected</c:if>>Ngày vào làm</option>
    </select>
    <select name="sortOrder">
        <option value="asc" <c:if test="${param.sortOrder == 'asc'}">selected</c:if>>Tăng dần</option>
        <option value="desc" <c:if test="${param.sortOrder == 'desc'}">selected</c:if>>Giảm dần</option>
    </select>
    <button type="submit">Sắp xếp</button>
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
