<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sổ Tay Chi Tiêu</title>
    <style>
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            font-family: Arial, sans-serif;
        }

        /* Định dạng cho form và các input */
        #transaction-form input,
        #transaction-form select,
        #transaction-form button {
            width: 100%; /* Mở rộng chiều rộng ra 100% */
            padding: 10px;
            margin-bottom: 15px; /* Thêm khoảng trống dưới */
            box-sizing: border-box; /* Đảm bảo padding không làm tăng chiều rộng */
        }

        #transaction-form button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        /* Định dạng thêm cho các phần khác (tùy chọn) */
        h1 {
            text-align: center;
        }

        .summary {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .summary-item {
            text-align: center;
        }

        .transactions {
            border-top: 2px solid #333;
            padding-top: 15px;
        }

        #transaction-list {
            list-style-type: none;
            padding: 0;
        }

        #transaction-list li {
            padding: 10px;
            margin-bottom: 5px;
            border: 1px solid #eee;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .income .amount {
            color: #4CAF50;
            font-weight: bold;
        }

        .expense .amount {
            color: #f44336;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sổ Tay Chi Tiêu</h1>

    <c:set var="totalIncome" value="0"/>
    <c:set var="totalExpense" value="0"/>
    <c:forEach var="transaction" items="${transactions}">
        <c:if test="${transaction.getTypeName() == 'Thu'}">
            <c:set var="totalIncome" value="${totalIncome + transaction.getAmount()}"/>
        </c:if>
        <c:if test="${transaction.getTypeName() == 'Chi'}">
            <c:set var="totalExpense" value="${totalExpense + transaction.getAmount()}"/>
        </c:if>
    </c:forEach>
    <c:set var="totalBalance" value="${totalIncome - totalExpense}"/>

    <div class="summary">
        <div class="summary-item total">
            <h3>Tổng số dư:</h3>
            <p id="total-balance">
                <fmt:formatNumber value="${totalBalance}" type="currency" currencySymbol="₫"/>
            </p>
        </div>
        <div class="summary-item income">
            <h3>Tổng thu:</h3>
            <p id="total-income">
                <fmt:formatNumber value="${totalIncome}" type="currency" currencySymbol="₫"/>
            </p>
        </div>
        <div class="summary-item expense">
            <h3>Tổng chi:</h3>
            <p id="total-expense">
                <fmt:formatNumber value="${totalExpense}" type="currency" currencySymbol="₫"/>
            </p>
        </div>
    </div>

    <form id="transaction-form" action="add" method="post">
        <input type="number" name="amount" placeholder="Số tiền" required>
        <input type="text" name="description" placeholder="Mô tả" required>
        <input type="date" name="date" required>
        <select name="type" required>
            <c:forEach var="entry" items="${transactionTypes}">
                <option value="${entry.key}">${entry.value}</option>
            </c:forEach>
        </select>
        <button type="submit">Thêm giao dịch</button>
    </form>

    <div class="transactions">
        <h2>Danh sách giao dịch</h2>
        <ul id="transaction-list">
            <c:forEach var="transaction" items="${transactions}">
                <li class="<c:out value="${transaction.getTypeName() == 'Thu' ? 'income' : 'expense'}"/>">
                    <span><c:out value="${transaction.getDescription()}"/></span>
                    <span class="date"><fmt:formatDate value="${transaction.getDate()}" pattern="dd-MM-yyyy"/></span>
                    <span class="amount">
                        <c:out value="${transaction.getTypeName() == 'Thu' ? '+' : '-'}"/>
                        <fmt:formatNumber value="${transaction.getAmount()}" type="currency" currencySymbol="₫"/>
                    </span>
                    <div class="transaction-actions">
                        <a href="delete?id=${transaction.getId()}" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>