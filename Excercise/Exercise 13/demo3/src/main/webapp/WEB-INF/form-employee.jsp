<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><c:if test="${employee != null}">Sửa</c:if><c:if test="${employee == null}">Thêm mới</c:if> Nhân viên</h2>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form action="<c:if test="${employee != null}">edit-employee</c:if><c:if test="${employee == null}">create-employee</c:if>" method="post">
    <c:if test="${employee != null}">
        <input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
    </c:if>

    <label for="name">Tên Nhân viên:</label><br>
    <input type="text" id="name" name="name" value="<c:out value='${employee.name}' />" required /><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" value="<c:out value='${employee.email}' />" required /><br><br>

    <label for="salary">Lương:</label><br>
    <input type="number" id="salary" name="salary" value="<c:out value='${employee.salary}' />" step="0.01" required /><br><br>

    <label for="hireDate">Ngày vào làm:</label><br>
    <input type="date" id="hireDate" name="hireDate" value="<c:out value='${employee.hireDate}' />" required /><br><br>

    <label for="departmentId">Phòng ban:</label><br>
    <select id="departmentId" name="departmentId">
        <option value="">-- Chọn phòng ban --</option>
        <c:forEach var="dept" items="${listDepartments}">
            <option value="<c:out value='${dept.id}' />" <c:if test="${dept.id == employee.departmentId}">selected</c:if>>
                <c:out value="${dept.name}" />
            </option>
        </c:forEach>
    </select><br><br>

    <input type="submit" value="Lưu" />
</form>
</body>
</html>
