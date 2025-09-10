<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sổ Tay Chi Tiêu</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            color: #333;
            line-height: 1.6;
        }

        .container {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px 30px;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            text-align: center;
            color: #2c3e50;
        }
        /* Style cho phần cảnh báo */
        .warning-message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
        }

        /* Phần tổng*/
        .summary {
            display: flex;
            justify-content: space-around;
            gap: 15px;
            margin-bottom: 30px;
        }

        .summary-item {
            flex-grow: 1;
            text-align: center;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s;
        }

        .summary-item:hover {
            transform: translateY(-5px);
        }

        .summary-item h3 {
            margin-top: 0;
            color: #7f8c8d;
            font-size: 1em;
        }

        .summary-item p {
            font-size: 1.8em;
            font-weight: bold;
            margin: 5px 0 0;
        }

        .total { background-color: #ecf0f1; }
        .total p { color: #34495e; }
        .income { background-color: #e8f5e9; }
        .income p { color: #27ae60; }
        .expense { background-color: #fbecec; }
        .expense p { color: #c0392b; }

        /* Form nhập liệu */
        #transaction-form {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 15px;
            margin-bottom: 30px;
        }

        #transaction-form input,
        #transaction-form select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 1em;
        }

        #transaction-form input[type="text"],
        #transaction-form input[type="number"],
        #transaction-form input[type="date"],
        #transaction-form select {
            grid-column: span 1;
        }

        #transaction-form button {
            grid-column: 1 / -1;
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

        #transaction-form button:hover {
            background-color: #2980b9;
        }

        /* Bảng danh sách giao dịch */
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            background-color: #fdfdfd;
            border: none;
        }

        thead tr {
            background-color: #ecf0f1;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
        }

        tbody tr {
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s;
        }

        tbody tr:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        th {
            font-weight: bold;
            color: #7f8c8d;
        }

        td:first-child { border-top-left-radius: 8px; border-bottom-left-radius: 8px; }
        td:last-child { border-top-right-radius: 8px; border-bottom-right-radius: 8px; }

        .delete-link {
            color: #e74c3c;
            text-decoration: none;
        }
        .edit-link {
            color: #3498db;
            text-decoration: none;
        }

        .filter-controls {
            text-align: right;
            margin-bottom: 20px;
        }

        .filter-controls select {
            padding: 8px;
            border-radius: 5px;
        }

        /*dòng giao dịch*/
        .income-row {
            color: #27ae60;
            font-weight: 500;
        }

        .expense-row {
            color: #c0392b;
            font-weight: 500;
        }

        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            color: #3498db;
            padding: 8px 16px;
            text-decoration: none;
            border: 1px solid #ddd;
            margin: 0 4px;
            border-radius: 5px;
        }
        .pagination a.active {
            background-color: #3498db;
            color: white;
            border-color: #3498db;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sổ Tay Chi Tiêu</h1>

    <%-- Hiển thị thông báo cảnh báo nếu có --%>
    <c:if test="${not empty sessionScope.warningMessage}">
        <div class="warning-message">
            <c:out value="${sessionScope.warningMessage}"/>
        </div>
        <c:remove var="warningMessage" scope="session"/>
    </c:if>

    <c:set var="totalIncome" value="0"/>
    <c:set var="totalExpense" value="0"/>
    <c:forEach var="transaction" items="${transactions}">
        <c:if test="${transaction.getType() eq 'Thu'}">
            <c:set var="totalIncome" value="${totalIncome + transaction.getAmount()}"/>
        </c:if>
        <c:if test="${transaction.getType() eq 'Chi'}">
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
        <input type="date" name="date" required value="<c:out value="${sessionScope.pendingTransaction.date}"/>">
        <input type="number" name="amount" placeholder="Số tiền" required value="<c:out value="${sessionScope.pendingTransaction.amount}"/>">
        <input type="text" name="categories" id="categories-input" placeholder="Tên thu chi" list="categories-suggestions" required value="<c:out value="${sessionScope.pendingTransaction.categories}"/>">
        <datalist id="categories-suggestions"></datalist>
        <input type="text" name="description" placeholder="Mô tả" required value="<c:out value="${sessionScope.pendingTransaction.description}"/>">
        <select name="type" required>
            <option value="Thu" <c:if test="${sessionScope.pendingTransaction.type eq 'Thu'}">selected</c:if>>Thu</option>
            <option value="Chi" <c:if test="${sessionScope.pendingTransaction.type eq 'Chi'}">selected</c:if>>Chi</option>
        </select>
        <button type="submit">Thêm giao dịch</button>
        <input type="hidden" name="confirm_add" id="confirm-add-input" value="false">
    </form>

    <%-- Xóa đối tượng giao dịch tạm khỏi session sau khi sử dụng --%>
    <c:remove var="pendingTransaction" scope="session"/>

    <h2>Danh sách Giao dịch</h2>
    <div class="filter-controls">
        <form action="${pageContext.request.contextPath}/list" method="get">
            Lọc theo:
            <select name="type" onchange="this.form.submit()">
                <option value="all" ${currentFilter == 'all' ? 'selected' : ''}>Tất cả</option>
                <option value="Thu" ${currentFilter == 'Thu' ? 'selected' : ''}>Thu</option>
                <option value="Chi" ${currentFilter == 'Chi' ? 'selected' : ''}>Chi</option>
            </select>
        </form>
    </div>
    <br>

    <table>
        <thead>
        <tr>
            <th>Ngày</th>
            <th>Tên thu chi</th>
            <th>Số tiền</th>
            <th>Mô tả</th>
            <th>Loại</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="transaction" items="${transactions}">
            <tr class="<c:out value="${transaction.type == 'Thu' ? 'income-row' : 'expense-row'}" />">
                <td><c:out value="${transaction.date}"/></td>
                <td><c:out value="${transaction.categories}"/></td>
                <td><c:out value="${transaction.amount}"/></td>
                <td><c:out value="${transaction.description}"/></td>
                <td><c:out value="${transaction.type}"/></td>
                <td>
                    <a href="edit?id=<c:out value='${transaction.id}' />" class="edit-link">Sửa</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/delete?id=<c:out value='${transaction.id}'/>" class="delete-link" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const categoriesInput = document.getElementById('categories-input');
        const categoriesDatalist = document.getElementById('categories-suggestions');
        const transactionForm = document.getElementById('transaction-form');

        if (!categoriesInput || !categoriesDatalist || !transactionForm) {
            console.error("Lỗi: Không tìm thấy các phần tử cần thiết.");
            return;
        }

        let suggestions = JSON.parse(localStorage.getItem('categoriesSuggestions')) || [];

        function updateDatalist() {
            categoriesDatalist.innerHTML = '';
            suggestions.sort();
            suggestions.forEach(suggestion => {
                let option = document.createElement('option');
                option.value = suggestion;
                categoriesDatalist.appendChild(option);
            });
        }

        updateDatalist();

        const pendingDate = document.querySelector('input[name="date"]').value;
        const pendingAmount = document.querySelector('input[name="amount"]').value;
        const pendingCategories = document.querySelector('input[name="categories"]').value;
        const pendingDescription = document.querySelector('input[name="description"]').value;

        if (pendingDate === "" && pendingAmount === "" && pendingCategories === "" && pendingDescription === "") {
            const today = new Date();
            const year = today.getFullYear();
            const month = String(today.getMonth() + 1).padStart(2, '0');
            const day = String(today.getDate()).padStart(2, '0');
            document.querySelector('input[name="date"]').value = `${year}-${month}-${day}`;
        }


        transactionForm.addEventListener('submit', function(e) {
            const warningMessageElement = document.querySelector('.warning-message');
            const confirmAddInput = document.getElementById('confirm-add-input');

            if (warningMessageElement && confirmAddInput.value === 'false') {
                e.preventDefault();

                const confirmation = confirm('CẢNH BÁO: Số tiền chi vượt quá số dư hiện có. Bạn có chắc chắn muốn thêm giao dịch này không?');
                if (confirmation) {
                    confirmAddInput.value = 'true';
                    this.submit();
                }
            }
        });

        // Cập nhật localStorage với tên danh mục mới khi submit form thành công
        transactionForm.addEventListener('submit', function() {
            const newCategories = categoriesInput.value.trim();
            if (newCategories && !suggestions.includes(newCategories)) {
                suggestions.push(newCategories);
                if (suggestions.length > 50) {
                    suggestions.shift();
                }
                localStorage.setItem('categoriesSuggestions', JSON.stringify(suggestions));
            }
        });
    });
</script>
</body>
</html>