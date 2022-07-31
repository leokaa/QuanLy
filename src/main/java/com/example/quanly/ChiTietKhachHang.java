package com.example.quanly;

public class ChiTietKhachHang {
    private int sttkh;
    private int sttctkh;
    private String ngay = null;
    private int soloc = 0;
    private int sothungmua = 0;
    private int sothungtra  = 0;
    private String sotientra = "";
    private String sotienno = "";

    public ChiTietKhachHang(int sttkh, int sttctkh, String ngay, int soloc, int sothungmua, int sothungtra, String sotientra, String sotienno) {
        this.sttkh = sttkh;
        this.sttctkh = sttctkh;
        this.ngay = ngay;
        this.soloc = soloc;
        this.sothungmua = sothungmua;
        this.sothungtra = sothungtra;
        this.sotientra = sotientra;
        this.sotienno = sotienno;
    }

    public ChiTietKhachHang(String sotientra,int soloc, int sothungmua  ,String ngay ){
        this.sotientra = sotientra;
        this.soloc = soloc;
        this.sothungmua = sothungmua;
        this.ngay = ngay;
    }

    public int getSttkh() {
        return sttkh;
    }

    public void setSttkh(int sttkh) {
        this.sttkh = sttkh;
    }

    public int getSttctkh() {
        return sttctkh;
    }

    public void setSttctkh(int sttctkh) {
        this.sttctkh = sttctkh;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSoloc() {
        return soloc;
    }

    public void setSoloc(int soloc) {
        this.soloc = soloc;
    }

    public int getSothungmua() {
        return sothungmua;
    }

    public void setSothungmua(int sothungmua) {
        this.sothungmua = sothungmua;
    }

    public int getSothungtra() {
        return sothungtra;
    }

    public void setSothungtra(int sothungtra) {
        this.sothungtra = sothungtra;
    }

    public String getSotientra() {
        return sotientra;
    }

    public void setSotientra(String sotientra) {
        this.sotientra = sotientra;
    }

    public String getSotienno() {
        return sotienno;
    }

    public void setSotienno(String sotienno) {
        this.sotienno = sotienno;
    }

}
