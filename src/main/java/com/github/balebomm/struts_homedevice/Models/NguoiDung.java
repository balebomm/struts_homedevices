package com.github.balebomm.struts_homedevice.Models;

public class NguoiDung {
  int id;
  String tendangnhap, loaiquyen;

  public String getLoaiquyen() {
    return this.loaiquyen;
  }
  public void setLoaiquyen(String loaiquyen) {
    this.loaiquyen = loaiquyen;
  }
  public NguoiDung(int id, String tendangnhap, String loaiquyen) {
    super();
    this.id = id;
    this.tendangnhap = tendangnhap;
    this.loaiquyen = loaiquyen;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTendangnhap() {
    return this.tendangnhap;
  }

  public void setTendangnhap(String tendangnhap) {
    this.tendangnhap = tendangnhap;
  }
}
