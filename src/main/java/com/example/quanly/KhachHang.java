package com.example.quanly;

public class KhachHang {
    private  int stt;
    private String tenkh = null;
    private String sdt;
    private String diachi = null;
    private int sothungno = 0;
    private String sotienno = "";
    private String ghichu =null;

    public KhachHang(int stt,String tenkh, String sdt, String diachi, int sothungno, String sotienno, String ghichu) {
        this.stt = stt;
        this.tenkh = tenkh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.sothungno = sothungno;
        this.sotienno = sotienno;
        this.ghichu = ghichu;
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
