package com.github.balebomm.struts_homedevice.Models;

public class DuLieuThietBi {
  int id, idthietbi;
  float giatri;
  String ngaytao;

  public int getId() {
    return this.id;
  }
  public void setId(int id) {
    this.id = id;
  }

  public int getIdthietbi() {
    return this.idthietbi;
  }

  public void setIdthietbi(int idthietbi) {
    this.idthietbi = idthietbi;
  }

  public float getGiatri() {
    return this.giatri;
  }

  public void setGiatri(float giatri) {
    this.giatri = giatri;
  }

  public String getNgaytao() {
    return this.ngaytao;
  }
  public void setNgaytao(String ngaytao) {
    this.ngaytao = ngaytao;
  }

  public DuLieuThietBi(int id, int idthietbi, float giatri, String ngaytao) {
    super();
    this.id = id;
    this.idthietbi = idthietbi;
    this.giatri = giatri;
    this.ngaytao = ngaytao;
  }
}
