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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/list")
public class ListMatBangServlet extends HttpServlet {
    private MatBangService matBangService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void init() {
        matBangService = new MatBangService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        List<MatBang> listMatBang = null;

        boolean isDateError = false;

        if ("search".equals(action)) {
            String loaiMatBang = request.getParameter("loaiMatBang");
            Integer tang = null;
            if (request.getParameter("tang") != null && !request.getParameter("tang").isEmpty()) {
                tang = Integer.parseInt(request.getParameter("tang"));
            }
            String ngayBatDauStr = request.getParameter("ngayBatDau");
            String ngayKetThucStr = request.getParameter("ngayKetThuc");

            Date ngayBatDau = null;
            if (ngayBatDauStr != null && !ngayBatDauStr.isEmpty()) {
                try {
                    LocalDate localDate = LocalDate.parse(ngayBatDauStr, DATE_FORMATTER);
                    ngayBatDau = Date.valueOf(localDate);
                } catch (DateTimeParseException e) {
                    request.setAttribute("searchError", "Ngày bắt đầu không hợp lệ. Vui lòng sử dụng định dạng dd/mm/yyyy.");
                    isDateError = true;
                }
            }

            Date ngayKetThuc = null;
            if (ngayKetThucStr != null && !ngayKetThucStr.isEmpty()) {
                try {
                    LocalDate localDate = LocalDate.parse(ngayKetThucStr, DATE_FORMATTER);
                    ngayKetThuc = Date.valueOf(localDate);
                } catch (DateTimeParseException e) {
                    request.setAttribute("searchError", "Ngày kết thúc không hợp lệ. Vui lòng sử dụng định dạng dd/mm/yyyy.");
                    isDateError = true;
                }
            }

            if (!isDateError) {
                listMatBang = matBangService.searchMatBang(loaiMatBang, tang, ngayBatDau, ngayKetThuc);
            }
        } else {
            listMatBang = matBangService.selectAllMatBang();
        }

        request.setAttribute("listMatBang", listMatBang);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}