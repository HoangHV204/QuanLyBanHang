/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
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
}
