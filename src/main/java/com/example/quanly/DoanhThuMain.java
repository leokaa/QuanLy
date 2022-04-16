package com.example.quanly;

import java.util.Date;

public class DoanhThuMain {
    private Date DT_ngay;
    private int DT_soluongban;
    private int DT_soluongtra;
    private int LH_malh;
    private String LH_ten;
    private int thanhtien;

    public DoanhThuMain(Date DT_ngay, int DT_soluongban, int DT_soluongtra, int LH_malh, String LH_ten, int thanhtien) {
        this.DT_ngay = DT_ngay;
        this.DT_soluongban = DT_soluongban;
        this.DT_soluongtra = DT_soluongtra;
        this.LH_malh = LH_malh;
        this.LH_ten = LH_ten;
        this.thanhtien = thanhtien;
    }

    public DoanhThuMain(){

    }

    public Date getDT_ngay() {
        return DT_ngay;
    }

    public void setDT_ngay(Date DT_ngay) {
        this.DT_ngay = DT_ngay;
    }

    public int getDT_soluongban() {
        return DT_soluongban;
    }

    public void setDT_soluongban(int DT_soluongban) {
        this.DT_soluongban = DT_soluongban;
    }

    public int getDT_soluongtra() {
        return DT_soluongtra;
    }

    public void setDT_soluongtra(int DT_soluongtra) {
        this.DT_soluongtra = DT_soluongtra;
    }

    public int getLH_malh() {
        return LH_malh;
    }

    public void setLH_malh(int LH_malh) {
        this.LH_malh = LH_malh;
    }

    public String getLH_ten() {
        return LH_ten;
    }

    public void setLH_ten(String LH_ten) {
        this.LH_ten = LH_ten;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}
