package com.github.balebomm.struts_homedevice.Actions;

import java.util.List;

import com.github.balebomm.struts_homedevice.DAO.ThietBiDAO;
import com.github.balebomm.struts_homedevice.Models.ThietBi;
import com.opensymphony.xwork2.ActionSupport;

public class ThietBiAction extends ActionSupport {
  int id;
  String tenthietbi, trangthai;
  List<ThietBi> listthietbi;

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

  public List<ThietBi> getListthietbi() {
    return this.listthietbi;
  }

  public void setListthietbi(List<ThietBi> listthietbi) {
    this.listthietbi = listthietbi;
  }

  public String list() {
    this.listthietbi = new ThietBiDAO().list();
    return "action-success";
  }

  public String store() {
    boolean result = new ThietBiDAO().add(new ThietBi(tenthietbi, trangthai));
    if (result == false) {
      addActionMessage("Có lỗi xảy ra trong quá trình xử lý");
      return "action-create";
    }

    return "action-list";
  }

  public String create() {
    return "action-success";
  }

  public String edit() {
    ThietBi thietbi = new ThietBiDAO().get(this.id);
    if (thietbi == null) {
      addActionMessage("Không tìm thấy dữ liệu");
      return "action-create";
    }

    this.tenthietbi = thietbi.getTenthietbi();
    this.trangthai = thietbi.getTrangthai();
    
    return "action-success";
  }

  public String update() {
    boolean ok = new ThietBiDAO().update(new ThietBi(this.id, this.tenthietbi, this.trangthai));
    if (!ok) {
      addActionMessage("Cập nhật dữ liệu thất bại");
      return "action-create";
    }

    return "action-list";
  }

  public String delete() {
    ThietBi thietbi = new ThietBiDAO().get(this.id);
    if (thietbi == null) {
      addActionMessage("Không tìm thấy dữ liệu");
      return "action-list";
    }

    this.tenthietbi = thietbi.getTenthietbi();
    this.trangthai = thietbi.getTrangthai();

    return "action-success";
  }

  public String destroy() {
    boolean ok = new ThietBiDAO().delete(this.id);
    if (!ok) {
      addActionMessage("Có lỗi khi thực hiện thao tác xóa");
      return "action-list";
    }

    addActionMessage("Xóa thành công");
    return "action-list";
  }
}
