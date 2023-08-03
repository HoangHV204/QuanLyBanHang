/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LE MINH
 */
public class SanPham {

    private String maSanPham;
    private String tenSanPham;
    private String loai;
    private int soLuong;
    private int giaBan;
    private String trangThai;
    private String image;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, String loai, int soLuong, int giaBan, String trangThai, String image) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.loai = loai;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.image = image;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
