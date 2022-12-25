package com.github.balebomm.struts_homedevice.Actions;

import java.util.List;

import com.github.balebomm.struts_homedevice.DAO.DuLieuThietBiDAO;
import com.github.balebomm.struts_homedevice.Models.DuLieuThietBi;
import com.opensymphony.xwork2.ActionSupport;

public class DuLieuThietBiAction extends ActionSupport {
  int id, idthietbi;
  float giatri;
  String ngaytao, fromdate, todate;
  List<DuLieuThietBi> listdulieuthietbi;

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
  
  public String getFromdate() {
    return this.fromdate;
  }
  public void setFromdate(String fromdate) {
    this.fromdate = fromdate;
  }
  
  public String getTodate() {
    return this.todate;
  }
  public void setTodate(String todate) {
    this.todate = todate;
  }
  
  public List<DuLieuThietBi> getListdulieuthietbi() {
    return this.listdulieuthietbi;
  }

  public void setListdulieuthietbi(List<DuLieuThietBi> listdulieuthietbi) {
    this.listdulieuthietbi = listdulieuthietbi;
  }

  public String list() {
    this.listdulieuthietbi = new DuLieuThietBiDAO().list(this.idthietbi, this.fromdate, this.todate);
    return "action-success";
  }
}
