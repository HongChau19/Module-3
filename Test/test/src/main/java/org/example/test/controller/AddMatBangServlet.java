package org.example.test.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.test.model.MatBang;
import org.example.test.service.MatBangService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@WebServlet("/add")
public class AddMatBangServlet extends HttpServlet {
    private MatBangService matBangService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void init() {
        matBangService = new MatBangService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maMatBang = request.getParameter("maMatBang");
        String dienTichParam = request.getParameter("dienTich");
        String giaThueParam = request.getParameter("giaThue");
        String ngayBatDauStr = request.getParameter("ngayBatDau");
        String ngayKetThucStr = request.getParameter("ngayKetThuc");
        String moTaChiTiet = request.getParameter("moTaChiTiet");
        
        if (matBangService.checkMaMatBang(maMatBang)) {
            request.setAttribute("message", "Lỗi: Mã mặt bằng đã tồn tại.");
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        double dienTich, giaThue;
        try {
            dienTich = Double.parseDouble(dienTichParam);
            if (dienTich <= 20) {
                request.setAttribute("message", "Lỗi: Diện tích phải lớn hơn 20m2.");
                request.getRequestDispatcher("add.jsp").forward(request, response);
                return;
            }

            giaThue = Double.parseDouble(giaThueParam);
            if (giaThue <= 1000000) {
                request.setAttribute("message", "Lỗi: Giá thuê phải lớn hơn 1.000.000 VNĐ.");
                request.getRequestDispatcher("add.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Lỗi: Diện tích và Giá thuê phải là số.");
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        LocalDate ngayBatDauLocalDate, ngayKetThucLocalDate;
        try {
            ngayBatDauLocalDate = LocalDate.parse(ngayBatDauStr, DATE_FORMATTER);
            ngayKetThucLocalDate = LocalDate.parse(ngayKetThucStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            request.setAttribute("message", "Lỗi: Định dạng ngày tháng không hợp lệ.");
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        long monthsBetween = ChronoUnit.MONTHS.between(ngayBatDauLocalDate, ngayKetThucLocalDate);
        if (monthsBetween < 6) {
            request.setAttribute("message", "Lỗi: Ngày kết thúc phải sau ngày bắt đầu ít nhất 6 tháng.");
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        try {
            Date ngayBatDau = Date.valueOf(ngayBatDauLocalDate);
            Date ngayKetThuc = Date.valueOf(ngayKetThucLocalDate);

            String trangThai = request.getParameter("trangThai");
            int tang = Integer.parseInt(request.getParameter("tang"));
            String loaiMatBang = request.getParameter("loaiMatBang");

            MatBang newMatBang = new MatBang(maMatBang, dienTich, trangThai, tang, loaiMatBang, giaThue, ngayBatDau, ngayKetThuc, moTaChiTiet);
            matBangService.insertMatBang(newMatBang);
            response.sendRedirect("list");

        } catch (SQLException e) {
            request.setAttribute("message", "Lỗi: Có lỗi xảy ra khi thêm dữ liệu vào cơ sở dữ liệu. Vui lòng kiểm tra lại cấu trúc bảng hoặc dữ liệu nhập vào.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}