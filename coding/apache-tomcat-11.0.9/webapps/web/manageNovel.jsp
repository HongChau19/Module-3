<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.web.service.NovelService" %>
<%@ page import="org.example.web.model.Novel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý truyện</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1, h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .add-novel-btn {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .add-novel-btn:hover { background-color: #45a049; }
        .actions a { margin-right: 10px; text-decoration: none; color: #007bff; }
        .actions a.delete { color: #dc3545; }
    </style>
</head>
<body>

<h1>Quản lý truyện</h1>

<h2>Danh sách truyện</h2>
<a href="addNovel.jsp" class="add-novel-btn">Thêm truyện mới</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tiêu đề</th>
        <th>Tác giả</th>
        <th>Thể loại</th>
        <th>Trạng thái</th>
        <th>Ảnh</th>
        <th>Nội dung</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <%
        NovelService novelService = new NovelService();
        List<Novel> novels = novelService.getAllNovels();
        for (Novel novel : novels) {
    %>
    <tr>
        <td><%= novel.getId() %></td>
        <td><%= novel.getTitle() %></td>
        <td><%= novel.getAuthor() %></td>
        <td><%= novel.getGenre() %></td>
        <td><%= novel.getStatus() %></td>
        <td><%= novel.getImage() %></td>
        <td><%= novel.getContent() %></td>
        <td class="actions">
            <a href="edit.jsp?id=<%= novel.getId() %>">Sửa</a>
            <a href="deleteNovel?id=<%= novel.getId() %>" class="delete" onclick="return confirm('Bạn có chắc chắn muốn xóa truyện này?');">Xóa</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>