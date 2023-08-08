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
import model.HoaDon;

/**
 *
 * @author LE MINH
 */
public class HoaDonRepository {

    private KhachHangRepository kh = new KhachHangRepository();

    public List<HoaDon> getAllHoaDon() {
        String query = """
                        SELECT MaHD,HOADON.MaSP,Loai,HOADON.SoLuong,HOADON.MaKH,HoTenKH,DiaChi,SĐT,ThanhTien
                        FROM dbo.HOADON JOIN dbo.KHACHHANG ON KHACHHANG.MaKH = HOADON.MaKH
                        		JOIN dbo.CHITIETSANPHAM ON CHITIETSANPHAM.MaChiTietSanPham = HOADON.MaSP
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            List<HoaDon> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean addHoaDon(HoaDon hd, String maNV) {
        if (hd == null) {
            return false;
        }
        String query = "{CALL dbo.SP_AddHoaDon(?,?,?,?,?,?)}";
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setInt(1, hd.getMaHD());
            stm.setString(2, hd.getMaKH());
            System.out.println(hd.getMaKH());
            stm.setString(3, maNV);
            stm.setString(4, hd.getMaSP());
            stm.setInt(5, hd.getSoLuong());
            stm.setInt(6, hd.getThanhTien());
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }

    public boolean updateHoaDon(HoaDon hd, String maNV) {
        String query = """
                       UPDATE dbo.HOADON
                       SET MaSP = ?, MaNV = ?, ThanhTien = ?, SoLuong = ?, NgayTao = CAST(GETDATE() AS DATE)
                       WHERE MaHD = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setString(1, hd.getMaSP());
            stm.setString(2, maNV);
            stm.setInt(3, hd.getThanhTien());
            stm.setInt(4, hd.getSoLuong());
            stm.setInt(5, hd.getMaHD());
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }

    public boolean deleteHoaDon(int maHD) {
        String query = """
                       DELETE FROM dbo.HOADON
                       WHERE MaHD = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); CallableStatement stm = con.prepareCall(query)) {
            stm.setInt(1, maHD);
            check = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return check > 0;
    }

    public int getMaHoaDon() {
        String query = "SELECT MAX(MaHD) FROM dbo.HOADON";
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

    public List<HoaDon> getAllHoaDonHomQua() {
        String query = """
                        SELECT MaHD,CHITIETSANPHAM.TenSP,Loai,HOADON.SoLuong,CHITIETSANPHAM.DonGia,HoTenKH,DiaChi,SĐT,ThanhTien
                        FROM dbo.HOADON JOIN dbo.KHACHHANG ON KHACHHANG.MaKH = HOADON.MaKH
                                        JOIN dbo.CHITIETSANPHAM ON CHITIETSANPHAM.MaChiTietSanPham = HOADON.MaSP
                        WHERE NgayTao = CAST(DATEADD(day, -1, GETDATE()) AS DATE)
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            List<HoaDon> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<HoaDon> getAllHoaDonHomNay() {
        String query = """
                        SELECT MaHD,CHITIETSANPHAM.TenSP,Loai,HOADON.SoLuong,CHITIETSANPHAM.DonGia,HoTenKH,DiaChi,SĐT,ThanhTien
                        FROM dbo.HOADON JOIN dbo.KHACHHANG ON KHACHHANG.MaKH = HOADON.MaKH
                                        JOIN dbo.CHITIETSANPHAM ON CHITIETSANPHAM.MaChiTietSanPham = HOADON.MaSP
                        WHERE NgayTao = CAST(GETDATE() AS DATE)
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            List<HoaDon> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
