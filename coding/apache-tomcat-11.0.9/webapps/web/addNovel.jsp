<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Truyện Mới</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            /* Gradient xám nhạt */
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 100%;
            max-width: 650px;
            margin: 20px;
            /* Nền trong suốt trên nền gradient */
            background: rgba(255, 255, 255, 0.8);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(0, 0, 0, 0.05);
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
            font-weight: 600;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #555;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            background: #fcfcfc;
            border: 1px solid #ddd;
            color: #333;
            border-radius: 6px;
            box-sizing: border-box;
            transition: border-color 0.3s ease-in-out;
        }

        input[type="text"]:focus, textarea:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
        }

        textarea {
            height: 150px;
            resize: vertical;
        }

        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
            font-size: 1em;
            font-weight: 600;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #4cae4c;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 25px;
            color: #555;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .back-link:hover {
            color: #007bff;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Thêm Truyện Mới</h1>
    <form action="addNovel" method="post">
        <label for="title">Tiêu đề:</label>
        <input type="text" id="title" name="title" required>

        <label for="author">Tác giả:</label>
        <input type="text" id="author" name="author" required>

        <label for="genre">Thể loại:</label>
        <input type="text" id="genre" name="genre">

        <label for="status">Trạng thái:</label>
        <input type="text" id="status" name="status">

        <label for="image">Ảnh (URL):</label>
        <input type="text" id="image" name="image">

        <label for="content">Nội dung:</label>
        <textarea id="content" name="content" required></textarea>

        <input type="submit" value="Thêm truyện">
    </form>
    <a href="manageNovel.jsp" class="back-link">Quay lại trang quản lý</a>
</div>
</body>
</html>