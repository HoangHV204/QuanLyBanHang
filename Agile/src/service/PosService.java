/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import model.SanPham;
import repository.HoaDonRepository;
import repository.KhachHangRepository;
import repository.NhanVienRepository;
import repository.PosAgileRepository;
import repository.SanPhamRepository;

/**
 *
 * @author LE MINH
 */
public class PosService implements PosInterface {

    private final PosAgileRepository respository = new PosAgileRepository();
    private final HoaDonRepository hd = new HoaDonRepository();
    private final KhachHangRepository kh = new KhachHangRepository();
    private final SanPhamRepository sp = new SanPhamRepository();
    private final NhanVienRepository nv = new NhanVienRepository();

    @Override
    public boolean checkLogin(String account, String password) {
        return respository.checkLogin(account, password);
    }

    @Override
    public NhanVien getNhanVien(String maNV) {
        return nv.getNhanVien(maNV);
    }

    @Override
    public List<NhanVien> getAllNhanVien() {
        return nv.getAllNhanVien();
    }

    @Override
    public boolean addNhanVien(NhanVien nv) {
        return this.nv.addNhanVien(nv);
    }

    @Override
    public boolean updateNhanVien(NhanVien nv) {
        return this.nv.updateNhanVien(nv);
    }

    @Override
    public boolean deleteNhanVien(String maNV) {
        return nv.deleteNhanVien(maNV);
    }

    @Override
    public List<NhanVien> findNhanVien(String tenNV) {
        List<NhanVien> listFindNhanVien = new ArrayList<>();
        for (NhanVien nv : this.getAllNhanVien()) {
            if (nv.getTenNV().contains(tenNV)) {
                listFindNhanVien.add(nv);
            }
        }
        return listFindNhanVien;
    }

    @Override
    public String getRole(String maNV) {
        return respository.getRole(maNV);
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return sp.getAllSanPham();
    }

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hd.getAllHoaDon();
    }

    @Override
    public int getIdKhachHang() {
        return kh.getIdKhachHang();
    }

    @Override
    public int getMaHoaDon() {
        return hd.getMaHoaDon();
    }

    @Override
    public boolean addKhachHang(KhachHang kh) {
        return this.kh.addKhachHang(kh);
    }

    @Override
    public boolean addHoaDon(HoaDon hd, String maNV) {
        return this.hd.addHoaDon(hd, maNV);
    }

    @Override
    public boolean updateHoaDon(HoaDon hd, String maNV) {
        return this.hd.updateHoaDon(hd, maNV);
    }

    @Override
    public boolean deleteHoaDon(int maHD) {
        return this.hd.deleteHoaDon(maHD);
    }

    @Override
    public boolean addSanPham(SanPham sp) {
        return this.sp.addSanPham(sp);
    }

    @Override
    public boolean updateSanPham(SanPham sp) {
        return this.sp.updateSanPham(sp);
    }

    @Override
    public boolean deleteSanPham(String maSP) {
        return sp.deleteSanPham(maSP);
    }

    @Override
    public int getMaSanPham() {
        return sp.getMaSanPham();
    }

    @Override
    public List<SanPham> findSanPham(String maSP) {
        List<SanPham> listFindSanPham = new ArrayList<>();
        for (SanPham sp : this.getAllSanPham()) {
            if (maSP.equalsIgnoreCase(sp.getMaSanPham())) {
                listFindSanPham.add(sp);
            }
        }
        return listFindSanPham;
    }

    @Override
    public List<KhachHang> getAllKhachHang() {
        return kh.getAllKhachHang();
    }

    @Override
    public boolean updateKhachHang(KhachHang kh) {
        return this.kh.updateKhachHang(kh);
    }

    @Override
    public List<KhachHang> findKhachHang(String tenKH) {
        List<KhachHang> listFindKhachHang = new ArrayList<>();
        for (KhachHang kh : this.getAllKhachHang()) {
            if (kh.getHoTen().contains(tenKH)) {
                listFindKhachHang.add(kh);
            }
        }
        return listFindKhachHang;
    }

    public String yesterday() {
        final Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
    
    public String today() {
        final Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(cal.getTime());
    }

    public static void main(String[] args) {
        PosService service = new PosService();
        System.out.println(service.today());
    }

    @Override
    public List<HoaDon> getAllHoaDonHomQua() {
        return hd.getAllHoaDonHomQua();
    }

    @Override
    public List<HoaDon> getAllHoaDonHomNay() {
        return hd.getAllHoaDonHomNay();
    }
}
