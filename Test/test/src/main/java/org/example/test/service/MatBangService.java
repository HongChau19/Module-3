package org.example.test.service;

import org.example.test.model.MatBang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.web.service.DBConnection.getConnection;

public class MatBangService {
    private static final String INSERT_MAT_BANG_SQL = "INSERT INTO mat_bang (ma_mat_bang, dien_tich, trang_thai, tang, loai_mat_bang, gia_thue, ngay_bat_dau, ngay_ket_thuc, mo_ta_chi_tiet) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_MAT_BANG_SQL = "SELECT * FROM mat_bang ORDER BY dien_tich ASC;";
    private static final String DELETE_MAT_BANG_SQL = "DELETE FROM mat_bang WHERE ma_mat_bang = ?;";
    private static final String SELECT_MAT_BANG_BY_ID_SQL = "SELECT ma_mat_bang FROM mat_bang WHERE ma_mat_bang = ?;";



    public void insertMatBang(MatBang matBang) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MAT_BANG_SQL)) {
            preparedStatement.setString(1, matBang.getMaMatBang());
            preparedStatement.setDouble(2, matBang.getDienTich());
            preparedStatement.setString(3, matBang.getTrangThai());
            preparedStatement.setInt(4, matBang.getTang());
            preparedStatement.setString(5, matBang.getLoaiMatBang());
            preparedStatement.setDouble(6, matBang.getGiaThue());
            preparedStatement.setDate(7, matBang.getNgayBatDau());
            preparedStatement.setDate(8, matBang.getNgayKetThuc());
            preparedStatement.setString(9, matBang.getMoTaChiTiet());
            preparedStatement.executeUpdate();
        }
    }

    public List<MatBang> selectAllMatBang() {
        List<MatBang> listMatBang = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MAT_BANG_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maMatBang = rs.getString("ma_mat_bang");
                double dienTich = rs.getDouble("dien_tich");
                String trangThai = rs.getString("trang_thai");
                int tang = rs.getInt("tang");
                String loaiMatBang = rs.getString("loai_mat_bang");
                double giaThue = rs.getDouble("gia_thue");
                Date ngayBatDau = rs.getDate("ngay_bat_dau");
                Date ngayKetThuc = rs.getDate("ngay_ket_thuc");
                String moTaChiTiet = rs.getString("mo_ta_chi_tiet");
                listMatBang.add(new MatBang(maMatBang, dienTich, trangThai, tang, loaiMatBang, giaThue, ngayBatDau, ngayKetThuc, moTaChiTiet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMatBang;
    }

    public boolean deleteMatBang(String maMatBang) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MAT_BANG_SQL)) {
            statement.setString(1, maMatBang);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean checkMaMatBang(String maMatBang) {
        boolean exists = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAT_BANG_BY_ID_SQL)) {
            preparedStatement.setString(1, maMatBang);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public List<MatBang> searchMatBang(String loaiMatBang, Integer tang, Date ngayBatDau, Date ngayKetThuc) {
        List<MatBang> listMatBang = new ArrayList<>();
        String sql = "SELECT * FROM mat_bang WHERE 1=1";

        if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
            sql += " AND loaiMatBang = ?";
        }
        if (tang != null) {
            sql += " AND tang = ?";
        }
        if (ngayBatDau != null) {
            sql += " AND ngayBatDau >= ?";
        }
        if (ngayKetThuc != null) {
            sql += " AND ngayKetThuc <= ?";
        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            int index = 1;
            if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
                preparedStatement.setString(index++, loaiMatBang);
            }
            if (tang != null) {
                preparedStatement.setInt(index++, tang);
            }
            if (ngayBatDau != null) {
                preparedStatement.setDate(index++, ngayBatDau);
            }
            if (ngayKetThuc != null) {
                preparedStatement.setDate(index++, ngayKetThuc);
            }

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String maMatBang = rs.getString("maMatBang");
                double dienTich = rs.getDouble("dienTich");
                String trangThai = rs.getString("trangThai");
                int tangDb = rs.getInt("tang");
                String loaiMatBangDb = rs.getString("loaiMatBang");
                double giaThue = rs.getDouble("giaThue");
                Date ngayBatDauDb = rs.getDate("ngayBatDau");
                Date ngayKetThucDb = rs.getDate("ngayKetThuc");
                String moTaChiTiet = rs.getString("moTaChiTiet");

                listMatBang.add(new MatBang(maMatBang, dienTich, trangThai, tangDb, loaiMatBangDb, giaThue, ngayBatDauDb, ngayKetThucDb, moTaChiTiet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMatBang;
    }
}