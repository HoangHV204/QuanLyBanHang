/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author LE MINH
 */
public class NhanVienRepository {

    public NhanVien getNhanVien(String maNhanVien) {
        String query = """
                       SELECT * FROM dbo.NHANVIEN
                       WHERE MaNV = ?
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, maNhanVien);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                boolean gioiTinh = rs.getBoolean(3);
                Date ngaySinh = rs.getDate(4);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(6);
                String image = rs.getString(7);
                NhanVien nv = new NhanVien(ma, ten, gioiTinh, ngaySinh, diaChi, sdt, image);
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<NhanVien> getAllNhanVien() {
        String query = """
                       SELECT * FROM dbo.NHANVIEN
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            List<NhanVien> list = new ArrayList<>();
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                boolean gioiTinh = rs.getBoolean(3);
                Date ngaySinh = rs.getDate(4);
                String diaChi = rs.getString(5);
                String sdt = rs.getString(6);
                String image = rs.getString(7);
                NhanVien nv = new NhanVien(ma, ten, gioiTinh, ngaySinh, diaChi, sdt, image);
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addNhanVien(NhanVien nv) {
        if (nv != null) {
            String query = """
                       INSERT INTO [dbo].[NHANVIEN]
                                  ([MaNV]
                                  ,[HoTenNV]
                                  ,[GioiTinh]
                                  ,[NgaySinh]
                                  ,[DiaChi]
                                  ,[SĐT]
                                  ,[image])
                            VALUES (?,?,?,?,?,?,?)
                       """;
            int check = 0;
            try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
                stm.setString(1, nv.getMaNV());
                stm.setString(2, nv.getTenNV());
                stm.setBoolean(3, nv.isGioiTinh());
                stm.setDate(4, nv.getNgaySinh());
                stm.setString(5, nv.getDiaChi());
                stm.setString(6, nv.getSdt());
                stm.setString(7, nv.getImage());
                check = stm.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
            if (check > 0) {
                query = """
                       INSERT INTO [dbo].[USERS]
                                  ([username]
                                  ,[password]
                                  ,[role])
                            VALUES(?,?,?)
                       """;
                check = 0;
                try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
                    stm.setString(1, nv.getMaNV());
                    stm.setString(2, "123456");
                    stm.setString(3, "user");
                    check = stm.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }
            return check > 0;
        }
        return false;
    }

    public boolean updateNhanVien(NhanVien nv) {
        String query = """
                       UPDATE [dbo].[NHANVIEN]
                          SET [HoTenNV] = ?
                             ,[GioiTinh] = ?
                             ,[NgaySinh] = ?
                             ,[DiaChi] = ?
                             ,[SĐT] = ?
                        WHERE MaNV = ?
                       """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, nv.getTenNV());
            stm.setBoolean(2, nv.isGioiTinh());
            stm.setDate(3, nv.getNgaySinh());
            stm.setString(4, nv.getDiaChi());
            stm.setString(5, nv.getSdt());
            stm.setString(6, nv.getMaNV());
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteNhanVien(String maNV) {
        String query = """
                       DELETE FROM [dbo].[NHANVIEN]
                        WHERE MaNV = ?
                       """;
        int check = 0;
        deleteUser(maNV);
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, maNV);
            check = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public void deleteUser(String maNV) {
        String query = """
                       DELETE FROM dbo.USERS
                       WHERE username = ?
                       """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, maNV);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
