/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import model.SanPham;

/**
 *
 * @author LE MINH
 */
public interface PosInterface {

    boolean checkLogin(String account, String password);

    NhanVien getNhanVien(String maNV);

    List<NhanVien> getAllNhanVien();

    boolean addNhanVien(NhanVien nv);

    boolean updateNhanVien(NhanVien nv);

    boolean deleteNhanVien(String maNV);

    List<NhanVien> findNhanVien(String tenNV);

    String getRole(String maNV);

    List<SanPham> getAllSanPham();

    boolean addSanPham(SanPham sp);

    boolean updateSanPham(SanPham sp);

    boolean deleteSanPham(String maSP);

    int getMaSanPham();

    List<SanPham> findSanPham(String maSP);

    List<HoaDon> getAllHoaDon();

    List<KhachHang> getAllKhachHang();

    boolean updateKhachHang(KhachHang kh);

    List<KhachHang> findKhachHang(String tenKH);

    int getIdKhachHang();

    int getMaHoaDon();

    boolean addKhachHang(KhachHang kh);

    boolean addHoaDon(HoaDon hd, String maNV);

    boolean updateHoaDon(HoaDon hd, String maNV);

    boolean deleteHoaDon(int maHD);

    List<HoaDon> getAllHoaDonHomQua();

    List<HoaDon> getAllHoaDonHomNay();
}
