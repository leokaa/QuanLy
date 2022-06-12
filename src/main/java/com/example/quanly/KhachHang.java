package com.example.quanly;

import com.itextpdf.text.Document;

public class KhachHang extends Document {
    private  int stt;
    private String tenkh = null;
    private String sdt = null;
    private String diachi = null;
    private int sothungno = 0;
    private int sothungmuon = 0;
    private String tienban = "";
    private String sotienno = "";
    private String ghichu =null;

    public KhachHang(int stt, String tenkh, String sdt, String diachi, int sothungno, int sothungmuon, String tienban, String sotienno, String ghichu) {
        this.stt = stt;
        this.tenkh = tenkh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.sothungno = sothungno;
        this.sothungmuon = sothungmuon;
        this.tienban = tienban;
        this.sotienno = sotienno;
        this.ghichu = ghichu;
    }

    public KhachHang() {

    }

    public String getTienban() {
        return tienban;
    }

    public void setTienban(String tienban) {
        this.tienban = tienban;
    }

    public int getSothungmuon() {
        return sothungmuon;
    }

    public void setSothungmuon(int sothungmuon) {
        this.sothungmuon = sothungmuon;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSothungno() {
        return sothungno;
    }

    public void setSothungno(int sothungno) {
        this.sothungno = sothungno;
    }

    public String getSotienno() {
        return sotienno;
    }

    public void setSotienno(String sotienno) {
        this.sotienno = sotienno;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
