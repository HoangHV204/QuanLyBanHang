/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;

/**
 *
 * @author LE MINH
 */
public class KhachHangRepository {

    public List<KhachHang> getAllKhachHang() {
        String query = """
                       SELECT *
                       FROM dbo.KHACHHANG
                       ORDER BY CAST(SUBSTRING(MaKH,3,LEN(MaKH)) AS INT) ASC
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            List<KhachHang> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getString(6)));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean addKhachHang(KhachHang kh) {
        if (kh == null) {
            return false;
        }
        String query = "{CALL dbo.SP_AddKhachHang(?,?,?,?,?)}";
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setString(1, kh.getMaKH());
            stm.setString(2, kh.getHoTen());
            stm.setString(3, kh.getDiaChi());
            stm.setBoolean(4, kh.isGioiTinh());
            stm.setString(5, kh.getSdt());
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }

    public boolean updateKhachHang(KhachHang kh) {
        String query = """
                       UPDATE dbo.KHACHHANG
                       SET HoTenKH = ?, DiaChi = ? , Gioitinh = ?, SĐT = ?, image = ?
                       WHERE MaKH = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setString(1, kh.getHoTen());
            stm.setString(2, kh.getDiaChi());
            stm.setBoolean(3, kh.isGioiTinh());
            stm.setString(4, kh.getSdt());
            stm.setString(5, kh.getImage());
            stm.setString(6, kh.getMaKH());
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }

    public boolean deleteKhachHang(KhachHang kh) {
        String query = """
                       DELETE FROM dbo.KHACHHANG
                       WHERE MaKH = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setString(1, kh.getMaKH());
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }

    public int getIdKhachHang() {
        String query = """
                       SELECT MAX(CAST(SUBSTRING(MaKH,3,LEN(MaKH)) AS INT))
                       FROM dbo.KHACHHANG
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

}
