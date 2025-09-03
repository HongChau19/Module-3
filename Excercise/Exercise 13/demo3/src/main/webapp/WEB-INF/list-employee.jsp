<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách nhân viên</title>
</head>
<body>
<h2>Danh sách Nhân viên</h2>
<p><a href="create-employee">Thêm mới nhân viên</a></p>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form action="list-employees" method="get">
    <label for="departmentFilter">Lọc theo phòng ban:</label>
    <select id="departmentFilter" name="departmentId" onchange="this.form.submit()">
        <option value="">Tất cả</option>
        <c:forEach var="dept" items="${listDepartments}">
            <option value="<c:out value='${dept.id}' />" <c:if test="${dept.id == selectedDepartmentId}">selected</c:if>>
                <c:out value="${dept.name}" />
            </option>
        </c:forEach>
    </select>
</form>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Email</th>
        <th>Lương</th>
        <th>Ngày vào làm</th>
        <th>Phòng ban</th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="emp" items="${listEmployees}">
        <tr>
            <td><c:out value="${emp.id}" /></td>
            <td><c:out value="${emp.name}" /></td>
            <td><c:out value="${emp.email}" /></td>
            <td><c:out value="${emp.salary}" /></td>
            <td><c:out value="${emp.hireDate}" /></td>
            <td><c:out value="${emp.departmentName}" /></td>
            <td>
                <a href="edit-employee?id=<c:out value='${emp.id}' />">Sửa</a>
                &nbsp;&nbsp;&nbsp;
                <a href="delete-employee?id=<c:out value='${emp.id}' />" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
