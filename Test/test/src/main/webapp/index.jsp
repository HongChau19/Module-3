<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Quản lý Mặt bằng cho thuê</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .form-container {
            margin-bottom: 30px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
        }
    </style>
    <script>
        function confirmDelete(ma) {
            if (confirm("Bạn có chắc chắn muốn xóa mặt bằng với mã số " + ma + " không?")) {
                window.location.href = "delete?maMatBang=" + ma;
            }
        }

        function validateMaMatBang() {
            var maMatBang = document.getElementById("maMatBang").value;
            var maRegex = /^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$/;
            var errorElement = document.getElementById("maMatBangError");
            if (!maRegex.test(maMatBang)) {
                errorElement.textContent = "Mã mặt bằng phải đúng định dạng XXX-XX-XX, với X là số hoặc các ký tự alphabet viết hoa.";
                return false;
            }
            errorElement.textContent = "";
            return true;
        }

        function validateDienTich() {
            var dienTich = document.getElementById("dienTich").value;
            var errorElement = document.getElementById("dienTichError");
            if (parseFloat(dienTich) <= 20) {
                errorElement.textContent = "Diện tích phải lớn hơn 20m2.";
                return false;
            }
            errorElement.textContent = "";
            return true;
        }

        function validateGiaThue() {
            var giaThue = document.getElementById("giaThue").value;
            var errorElement = document.getElementById("giaThueError");
            if (parseFloat(giaThue) <= 1000000) {
                errorElement.textContent = "Giá tiền phải lớn hơn 1.000.000 VNĐ.";
                return false;
            }
            errorElement.textContent = "";
            return true;
        }

        function validateNgay() {
            var ngayBatDauStr = document.getElementById("ngayBatDau").value;
            var ngayKetThucStr = document.getElementById("ngayKetThuc").value;
            var errorElement = document.getElementById("ngayError");

            if (!ngayBatDauStr || !ngayKetThucStr) {
                errorElement.textContent = "Vui lòng chọn cả Ngày bắt đầu và Ngày kết thúc.";
                return false;
            }

            var ngayBatDau = new Date(ngayBatDauStr);
            var ngayKetThuc = new Date(ngayKetThucStr);

            if (ngayBatDau >= ngayKetThuc) {
                errorElement.textContent = "Ngày kết thúc phải sau ngày bắt đầu.";
                return false;
            }

            var diffInMonths = (ngayKetThuc.getFullYear() - ngayBatDau.getFullYear()) * 12 + (ngayKetThuc.getMonth() - ngayBatDau.getMonth());
            if (ngayKetThuc.getDate() < ngayBatDau.getDate()) {
                diffInMonths--;
            }

            if (diffInMonths < 6) {
                errorElement.textContent = "Ngày kết thúc phải sau ngày bắt đầu ít nhất 6 tháng.";
                return false;
            }

            errorElement.textContent = "";
            return true;
        }

        function validateFormOnSubmit() {
            var isMaMatBangValid = validateMaMatBang();
            var isDienTichValid = validateDienTich();
            var isGiaThueValid = validateGiaThue();
            var isNgayValid = validateNgay();
            return isMaMatBangValid && isDienTichValid && isGiaThueValid && isNgayValid;
        }
    </script>
</head>
<body>

<div class="form-container">
    <h2>Thêm mới mặt bằng cho thuê</h2>
    <form action="add" method="post" onsubmit="return validateFormOnSubmit()">
        <input type="hidden" name="action" value="add">
        <table>
            <tr>
                <td>Mã mặt bằng (*):</td>
                <td>
                    <input type="text" id="maMatBang" name="maMatBang" required onblur="validateMaMatBang()">
                    <br><span id="maMatBangError" class="error-message"></span>
                </td>
            </tr>
            <tr>
                <td>Diện tích (*):</td>
                <td>
                    <input type="number" id="dienTich" name="dienTich" required onblur="validateDienTich()">
                    <br><span id="dienTichError" class="error-message"></span>
                </td>
            </tr>
            <tr>
                <td>Trạng thái (*):</td>
                <td>
                    <select name="trangThai" required>
                        <option value="Trống">Trống</option>
                        <option value="Hạ tầng">Hạ tầng</option>
                        <option value="Đầy đủ">Đầy đủ</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Tầng (*):</td>
                <td>
                    <select name="tang" required>
                        <% for (int i = 1; i <= 15; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Loại mặt bằng (*):</td>
                <td>
                    <select name="loaiMatBang" required>
                        <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
                        <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Giá tiền (*):</td>
                <td>
                    <input type="number" id="giaThue" name="giaThue" required onblur="validateGiaThue()"> VND
                    <br><span id="giaThueError" class="error-message"></span>
                </td>
            </tr>
            <tr>
                <td>Ngày bắt đầu (*):</td>
                <td>
                    <input type="date" id="ngayBatDau" name="ngayBatDau" required onchange="validateNgay()">
                </td>
            </tr>
            <tr>
                <td>Ngày kết thúc (*):</td>
                <td>
                    <input type="date" id="ngayKetThuc" name="ngayKetThuc" required onchange="validateNgay()">
                    <br><span id="ngayError" class="error-message"></span>
                </td>
            </tr>
            <tr>
                <td>Mô tả chi tiết:</td>
                <td><textarea name="moTaChiTiet"></textarea></td>
            </tr>
            <tr>
                <td><input type="submit" value="Lưu"></td>
                <td><button type="button" onclick="window.location.href='list'">Hủy</button></td>
            </tr>
        </table>
    </form>
    <% if (request.getAttribute("message") != null) { %>
    <p style="color: red;"><%= request.getAttribute("message") %></p>
    <% } %>
</div>

<hr>

<div class="form-container">
    <h2>Tìm kiếm mặt bằng</h2>
    <form action="list" method="get">
        <input type="hidden" name="action" value="search">
        <table>
            <tr>
                <td>Loại Mặt bằng:</td>
                <td>
                    <select name="loaiMatBang">
                        <option value="">Tất cả</option>
                        <option value="Văn phòng chia sẻ" <c:if test="${param.loaiMatBang == 'Văn phòng chia sẻ'}">selected</c:if>>Văn phòng chia sẻ</option>
                        <option value="Văn phòng trọn gói" <c:if test="${param.loaiMatBang == 'Văn phòng trọn gói'}">selected</c:if>>Văn phòng trọn gói</option>
                    </select>
                </td>
                <td>Tầng:</td>
                <td>
                    <select name="tang">
                        <option value="">Tất cả</option>
                        <% for (int i = 1; i <= 15; i++) { %>
                        <option value="<%= i %>" <c:if test="${param.tang == i}">selected</c:if>><%= i %></option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Ngày cho thuê:</td>
                <td>Từ</td>
                <td><input type="text" name="ngayBatDau" placeholder="dd/mm/yyyy" value="${param.ngayBatDau}"></td>
                <td>Đến</td>
                <td><input type="text" name="ngayKetThuc" placeholder="dd/mm/yyyy" value="${param.ngayKetThuc}"></td>
            </tr>
            <tr>
                <td colspan="5" style="text-align: center;">
                    <input type="submit" value="Tìm kiếm">
                </td>
            </tr>
        </table>
    </form>
</div>

<hr>

<h2>Danh sách mặt bằng cho thuê</h2>

<table border="1">
    <thead>
    <tr>
        <th>Mã MB</th>
        <th>Diện tích</th>
        <th>Trạng thái</th>
        <th>Tầng</th>
        <th>Loại văn phòng</th>
        <th>Giá cho thuê</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="matBang" items="${listMatBang}">
        <tr>
            <td><c:out value="${matBang.maMatBang}" /></td>
            <td><c:out value="${matBang.dienTich}" /></td>
            <td><c:out value="${matBang.trangThai}" /></td>
            <td><c:out value="${matBang.tang}" /></td>
            <td><c:out value="${matBang.loaiMatBang}" /></td>
            <td><c:out value="${matBang.giaThue}" /></td>
            <td><fmt:formatDate value="${matBang.ngayBatDau}" pattern="dd/MM/yyyy" /></td>
            <td><fmt:formatDate value="${matBang.ngayKetThuc}" pattern="dd/MM/yyyy" /></td>
            <td>
                <button onclick="confirmDelete('${matBang.maMatBang}')">Xóa</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>