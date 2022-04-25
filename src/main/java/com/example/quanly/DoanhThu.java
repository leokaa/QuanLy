package com.example.quanly;

public class DoanhThu {
    private String ngay = null;
    private int soluongban = 0;
    private int soluongtra = 0;
    private String maLH = null;
    private  String tongdoanhthu = null;

    public DoanhThu(String ngay, int soluongban, int soluongtra, String maLH,String tongdoanhthu) {
        this.ngay = ngay;
        this.soluongban = soluongban;
        this.soluongtra = soluongtra;
        this.maLH = maLH;
        this.tongdoanhthu=tongdoanhthu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSoluongban() {
        return soluongban;
    }

    public void setSoluongban(int coluongban) {
        this.soluongban = soluongban;
    }

    public int getSoluongtra() {
        return soluongtra;
    }

    public void setSoluongtra(int soluongtra) {
        this.soluongtra = soluongtra;
    }

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getTongdoanhthu() {
        return tongdoanhthu;
    }

    public void setTongdoanhthu(String tongdoanhthu) {
        this.tongdoanhthu = tongdoanhthu;
    }
}