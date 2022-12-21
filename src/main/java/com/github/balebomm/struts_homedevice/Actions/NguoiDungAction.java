package com.github.balebomm.struts_homedevice.Actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.github.balebomm.struts_homedevice.DAO.NguoiDungDAO;
import com.github.balebomm.struts_homedevice.Models.NguoiDung;
import com.opensymphony.xwork2.ActionSupport;

public class NguoiDungAction extends ActionSupport implements SessionAware {
  String tendangnhap, matkhau;
  Map<String, Object> session;

  public void setSession(Map<String, Object> session) {
    this.session = session;
  }
  public String getTendangnhap() {
    return tendangnhap;
  }

  public void setTendangnhap(String tendangnhap) {
    this.tendangnhap = tendangnhap;
  }

  public String getMatkhau() {
    return this.matkhau;
  }

  public void setMatkhau(String matkhau) {
    this.matkhau = matkhau;
  }

  public Map<String, Object> getSession() {
    return session;
  }

  public String login() {
    NguoiDung nd = new NguoiDungDAO().login(this.tendangnhap, this.matkhau);
    if (nd == null) {
      addActionMessage("Bạn nhập sai tên đăng nhập hoặc mật khẩu!");
      return "fail";
    }

    this.session.put("nguoidung", nd);
    if (nd.getLoaiquyen().equals("admin")) {
      return "admin";
    }

    return "khach";
  }
}
