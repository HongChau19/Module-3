<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="./addemployee.jsp">Thêm nhân viên</a>
<c:if test="${not empty errors}">
    <ul>
        <c:forEach items="${errors}" var="message">
        <li>${message}</li>
        </c:forEach>
    </ul>
</c:if>
<form action="???" method="post">
    <c: if test="employee != null">
        <input type="hidden" name="id" value="${employee.id}">
    </c:>
    Họ tên:<input type="text" name="fullName" value="${employee.fullName}"/><br/>
    Email:<input type="text" name="email" value="${employee.email}"/><br/>
    Phone:<input type="text" name="phone" value="${employee.phone}"/><br/>
    Phòng ban:<input type="text" name="department" value="${employee.deparment}"/><br/>
    Lương:<input type="number" name="salary" value="${employee.salary}"/><br/>
    Ngày tuyển dụng:<input type="date" name="hireDate" value="${employee.hireDate}"/><br/>
    <button type="submit">Lưu</button>

</form>

</body>
</html>
