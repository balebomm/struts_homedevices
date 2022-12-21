package com.github.balebomm.struts_homedevice.Actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.github.balebomm.struts_homedevice.DAO.ThietBiDAO;
import com.github.balebomm.struts_homedevice.Models.ThietBi;
import com.opensymphony.xwork2.ActionSupport;

public class ThietBiAction extends ActionSupport implements SessionAware {
  String tenthietbi, trangthai;
  Map<String, Object> session;
  List<ThietBi> listthietbi;


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

  public List<ThietBi> getListthietbi() {
    return this.listthietbi;
  }

  public void setListthietbi(List<ThietBi> listthietbi) {
    this.listthietbi = listthietbi;
  }

  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public Map<String, Object> getSession() {
    return session;
  }

  public String list() {
    this.listthietbi = new ThietBiDAO().list();
    return "action-success";
  }

  public String store() {
    boolean result = new ThietBiDAO().add(new ThietBi(tenthietbi, trangthai));
    if (result == false) {
      addActionMessage("Có lỗi xảy ra trong quá trình xử lý");
      return "action-form";
    }

    return "action-list";
  }

  public String create() {
    return "action-success";
  }

  public String edit() {
    return "action-success";
  }
}
