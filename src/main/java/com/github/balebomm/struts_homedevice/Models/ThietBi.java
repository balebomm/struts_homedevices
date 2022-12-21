package com.github.balebomm.struts_homedevice.Models;

public class ThietBi {
  int id;
  String tenthietbi, trangthai;

  public int getId() {
    return this.id;
  }
  public void setId(int id) {
    this.id = id;
  }

  public String getTenthietbi() {
    return this.tenthietbi;
  }
  public void setTenthietbi(String tenthietbi) {
    this.tenthietbi = tenthietbi;
  }

  public String getTrangthai() {
    return this.trangthai;
  }
  public void setTrangthai(String trangthai) {
    this.trangthai = trangthai;
  }

  public ThietBi(int id, String tenthietbi, String trangthai) {
    super();
    this.id = id;
    this.tenthietbi = tenthietbi;
    this.trangthai = trangthai;
  }

  public ThietBi(String tenthietbi, String trangthai) {
    super();
    this.tenthietbi = tenthietbi;
    this.trangthai = trangthai;
  }
}
