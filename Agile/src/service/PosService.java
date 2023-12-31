/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public int findNhanVien(String maNV) {
        int findIndex = 0, check = 0;
        for (NhanVien item : this.getAllNhanVien()) {
            if (item.getMaNV().equalsIgnoreCase(maNV)) {
                check++; // Kiểm tra xem trong collection có tên cần tìm hay không
                break;
            } else {
                findIndex++;
            }
        }
        if (check == 0) {
            return -1;
        } else {
            return findIndex;
        }
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
    public int  findSanPham(String maSP) {
        int findIndex = 0, check = 0;
        for (SanPham item : this.getAllSanPham()) {
            if (item.getMaSanPham().equalsIgnoreCase(maSP)) {
                check++; // Kiểm tra xem trong collection có tên cần tìm hay không
                break;
            } else {
                findIndex++;
            }
        }
        if (check == 0) {
            return -1;
        } else {
            return findIndex;
        }
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

    @Override
    public boolean deleteKhachHang(KhachHang kh) {
        return this.kh.deleteKhachHang(kh);
    }

    @Override
    public boolean rememberPassword(String account) {
        return respository.rememberPassword(account);
    }

    @Override
    public boolean notRememberPassword(String account) {
        return respository.notRememberPassword(account);
    }

    @Override
    public String getPassword(String account) {
        return respository.getPassword(account);
    }

    @Override
    public String checkRememberPassword(String account) {
        return respository.checkRememberPassword(account);
    }
}
