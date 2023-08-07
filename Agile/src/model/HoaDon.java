/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LE MINH
 */
public class HoaDon {

    private int maHD;
    private String maSP;
    private String loaiSP;
    private int soLuong;
    private String maKH;
    private String tenKH;
    private String diaChiKH;
    private String soDT;
    private int thanhTien;

    public HoaDon() {
    }

    public HoaDon(int maHD, String maSP, String loaiSP, int soLuong, String maKH, String tenKH, String diaChiKH, String soDT, int thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.loaiSP = loaiSP;
        this.soLuong = soLuong;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChiKH = diaChiKH;
        this.soDT = soDT;
        this.thanhTien = thanhTien;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setHoaDon(int maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String tenSP) {
        this.maSP = tenSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

}
