package com.example.quanly;

import java.util.Date;

public class HangNhap {
    private Date NgayNhap;
    private String Ten_LH;
    private int SoLuong;
    private int DonGia;
    private int ThanhTien;
    private String GhiChu;

    public HangNhap(Date ngayNhap,String Ten_LH, int soLuong,int DonGia,int ThanhTien, String ghiChu) {
        this.NgayNhap = ngayNhap;
        this.Ten_LH = Ten_LH;
        this.SoLuong = soLuong;
        this.ThanhTien = ThanhTien;
        this.GhiChu = ghiChu;
        this.DonGia=DonGia;
    }

    public HangNhap() {

    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public String getTen_LH() {
        return Ten_LH;
    }

    public void setTen_LH(String ten_LH) {
        Ten_LH = ten_LH;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "HangNhap{" +
                ", Ten_LH=" + Ten_LH +
                ", NgayNhap=" + NgayNhap +
                ", SoLuong=" + SoLuong +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
