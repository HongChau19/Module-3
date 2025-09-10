<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sửa Giao Dịch</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            color: #333;
            line-height: 1.6;
        }

        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        form label {
            font-weight: bold;
            color: #555;
        }

        form input[type="text"],
        form input[type="number"],
        form input[type="date"],
        form select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 1em;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            padding: 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1.1em;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Sửa Giao Dịch</h2>
    <form action="edit" method="post">
        <input type="hidden" name="id" value="<c:out value='${transaction.id}' />" />

        <label for="categories">Tên thu chi:</label>
        <input type="text" id="categories" name="categories" value="<c:out value='${transaction.categories}' />" required>

        <label for="amount">Số tiền:</label>
        <input type="number" step="1" id="amount" name="amount" value="<fmt:formatNumber value='${transaction.amount}' pattern='#####' />" required>

        <label for="type">Loại:</label>
        <select id="type" name="type">
            <option value="Thu" <c:if test="${transaction.type eq 'Thu'}">selected</c:if>>Thu</option>
            <option value="Chi" <c:if test="${transaction.type eq 'Chi'}">selected</c:if>>Chi</option>
        </select>

        <label for="date">Ngày:</label>
        <input type="date" id="date" name="date" value="<c:out value='${transaction.date}' />" required>

        <label for="description">Mô tả:</label>
        <input type="text" id="description" name="description" value="<c:out value='${transaction.description}' />">

        <input type="submit" value="Cập nhật">
    </form>
    <a href="list" class="back-link">Quay lại danh sách</a>
</div>
</body>
</html>