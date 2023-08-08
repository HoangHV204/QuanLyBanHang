/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;

/**
 *
 * @author LE MINH
 */
public class SanPhamRepository {
    
    public List<SanPham> getAllSanPham() {
        String query = """
                       SELECT MaChiTietSanPham,TenSP,Loai,SoLuong,DonGia,TrangThai,image 
                       FROM dbo.CHITIETSANPHAM
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            List<SanPham> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new SanPham(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public boolean addSanPham(SanPham sp) {
        if (sp == null) {
            return false;
        }
        String query = """
                       INSERT INTO dbo.CHITIETSANPHAM
                       VALUES(?,?,?,?,?,?,?)
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setString(1, sp.getMaSanPham());
            stm.setString(2, sp.getTenSanPham());
            stm.setString(3, sp.getLoai());
            stm.setInt(4, sp.getGiaBan());
            stm.setInt(5, sp.getSoLuong());
            stm.setString(6, sp.getTrangThai());
            stm.setString(7, sp.getImage());
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }
    
    public boolean updateSanPham(SanPham sp) {
        if (sp == null) {
            return false;
        }
        String query = """
                       UPDATE dbo.CHITIETSANPHAM
                       SET TenSP = ?, Loai = ?, DonGia = ?, SoLuong = ?, TrangThai = ?
                       WHERE MaChiTietSanPham = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, sp.getTenSanPham());
            stm.setString(2, sp.getLoai());
            stm.setInt(3, sp.getGiaBan());
            stm.setInt(4, sp.getSoLuong());
            stm.setString(5, sp.getTrangThai());
            stm.setString(6, sp.getMaSanPham());
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }
    
    public boolean deleteSanPham(String maSP) {
        String query = """
                       DELETE FROM dbo.CHITIETSANPHAM
                       WHERE MaChiTietSanPham = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setString(1, maSP);
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }
    
    public int getMaSanPham() {
        String query = """
                       SELECT MAX(CAST(SUBSTRING(MaChiTietSanPham,3,LEN(MaChiTietSanPham)) AS INT))
                       FROM dbo.CHITIETSANPHAM
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
