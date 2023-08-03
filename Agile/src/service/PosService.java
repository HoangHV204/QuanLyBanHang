/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import model.SanPham;
import respository.PosAgileRespository;

/**
 *
 * @author LE MINH
 */
public class PosService implements PosInterface{
    
    private final PosAgileRespository respository = new PosAgileRespository();
    
    @Override
    public boolean checkLogin(String account, String password) {
        return respository.checkLogin(account, password);
    }
    
    @Override
    public NhanVien getNhanVien(String maNV) {
        return respository.getNhanVien(maNV);
    }

    @Override
    public List<NhanVien> getAllNhanVien() {
        return respository.getAllNhanVien();
    }

    @Override
    public boolean addNhanVien(NhanVien nv) {
        return respository.addNhanVien(nv);
    }

    @Override
    public boolean updateNhanVien(NhanVien nv) {
        return  respository.updateNhanVien(nv);
    }

    @Override
    public boolean deleteNhanVien(String maNV) {
        return respository.deleteNhanVien(maNV);
    }

    @Override
    public List<NhanVien> findNhanVien(String tenNV) {
        List<NhanVien> listFindNhanVien = new ArrayList<>();
        for (NhanVien nv : this.getAllNhanVien()) {
            if (nv.getTenNV().contains(tenNV)) {
                listFindNhanVien.add(nv);
            }
        }
        return  listFindNhanVien;
    }

    @Override
    public String getRole(String maNV) {
        return respository.getRole(maNV);
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return respository.getAllSanPham();
    }
    
    
        
}
