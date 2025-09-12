<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Chủ</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            /* Nền gradient tối */
            background: linear-gradient(135deg, #1f2838 0%, #0c0e12 100%);
            color: #c0c0c0; /* Màu chữ mặc định cho body */
            text-align: center;
            padding-top: 100px; /* Tăng padding để nội dung nằm giữa hơn */
            min-height: 100vh; /* Đảm bảo body chiếm toàn bộ chiều cao màn hình */
            display: flex; /* Dùng flexbox để căn giữa nội dung */
            flex-direction: column; /* Sắp xếp nội dung theo cột */
            justify-content: center; /* Căn giữa theo chiều dọc */
            align-items: center; /* Căn giữa theo chiều ngang */
        }

        .welcome-section {
            background: rgba(255, 255, 255, 0.05); /* Nền hơi trong suốt cho phần nội dung */
            padding: 40px 60px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            border: 1px solid rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(5px); /* Hiệu ứng mờ nền nhẹ */
            max-width: 600px;
            width: 90%; /* Responsive width */
        }

        h1 {
            font-size: 3.5em; /* Tăng kích thước chữ */
            font-weight: 700;
            margin-bottom: 25px;
            letter-spacing: 1px; /* Khoảng cách giữa các chữ cái */

            /* Hiệu ứng gradient cho chữ */
            background: linear-gradient(45deg, #a8c0ff, #3f2b96); /* Gradient từ xanh sáng sang tím đậm */
            -webkit-background-clip: text; /* Cắt nền theo hình dạng chữ */
            -webkit-text-fill-color: transparent; /* Làm chữ trong suốt để thấy nền */
            background-clip: text;
            color: transparent; /* Fallback cho trình duyệt không hỗ trợ */

            /* Đổ bóng cho chữ */
            text-shadow:
                    0 0 5px rgba(168, 192, 255, 0.5), /* Bóng sáng nhẹ */
                    0 0 10px rgba(63, 43, 150, 0.5); /* Bóng đậm hơn */
            transition: all 0.3s ease; /* Hiệu ứng chuyển động khi hover */
        }

        h1:hover {
            transform: scale(1.02); /* Phóng to nhẹ khi hover */
            text-shadow:
                    0 0 8px rgba(168, 192, 255, 0.7),
                    0 0 15px rgba(63, 43, 150, 0.7);
        }

        p {
            font-size: 1.3em; /* Tăng kích thước chữ */
            margin-bottom: 35px; /* Tăng khoảng cách dưới mỗi đoạn văn bản */
            line-height: 1.6; /* Tăng chiều cao dòng */
            color: #e0e0e0; /* Màu chữ sáng hơn trên nền tối */
        }

        .login-button {
            display: inline-block;
            color: white;
            text-decoration: none;
            font-weight: bold;
            padding: 15px 35px;
            border-radius: 8px;
            font-size: 1.1em;
            letter-spacing: 0.5px;
            background: linear-gradient(45deg, #007bff, #0056b3); /* Gradient cho nút */
            box-shadow: 0 5px 15px rgba(0, 123, 255, 0.4); /* Đổ bóng cho nút */
            transition: all 0.3s ease;
        }

        .login-button:hover {
            background: linear-gradient(45deg, #0056b3, #007bff); /* Đổi gradient khi hover */
            box-shadow: 0 8px 20px rgba(0, 123, 255, 0.6);
            transform: translateY(-3px); /* Nâng nút lên một chút */
        }
    </style>
</head>
<body>
<div class="welcome-section">
    <h1>Chào mừng bạn đến với trang Quản trị!</h1>
    <p>Vui lòng đăng nhập để tiếp tục quá trình quản lý hệ thống của bạn.</p>
    <p><a href="admin-login.jsp" class="login-button">Đăng Nhập Admin</a></p>
</div>
</body>
</html>