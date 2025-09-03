<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách sinh vin</title>
</head>
<body>
<table>
    <tr>
        <th>Mã SV</th>
        <th>Họ và tên</th>
        <th>Chuyên ngành</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.subject}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
