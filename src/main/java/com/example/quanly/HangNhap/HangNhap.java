package com.example.quanly.HangNhap;

import java.util.Date;

public class HangNhap {
    private int mahn;
    private Date NgayNhap;
    private String Ten_LH;
    private int SoLuong;
    private int ThanhTien;
    private String GhiChu;

    public HangNhap(int mahn, Date ngayNhap,String Ten_LH, int soLuong,int ThanhTien, String ghiChu) {
        this.mahn = mahn;
        this.NgayNhap = ngayNhap;
        this.Ten_LH = Ten_LH;
        this.SoLuong = soLuong;
        this.ThanhTien = ThanhTien;
        this.GhiChu = ghiChu;
    }


    public int getMahn() {
        return mahn;
    }

    public void setMahn(int mahn) {
        this.mahn = mahn;
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
                "mahn=" + mahn +
                ", Ten_LH=" + Ten_LH +
                ", NgayNhap=" + NgayNhap +
                ", SoLuong=" + SoLuong +
                ", GhiChu='" + GhiChu + '\'' +
                '}';
    }
}
