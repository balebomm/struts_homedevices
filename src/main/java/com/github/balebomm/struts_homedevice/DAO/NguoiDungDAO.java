package com.github.balebomm.struts_homedevice.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.balebomm.struts_homedevice.Models.NguoiDung;

public class NguoiDungDAO {
  public NguoiDung login(String tendangnhap, String matkhau) {
    NguoiDung nd = null;
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("SELECT * FROM nguoidung WHERE tendangnhap = ? AND matkhau = ?");
      stm.setString(1, tendangnhap);
      stm.setString(2, matkhau);
      ResultSet rs = db.executeQuery(stm);
      if (rs != null) {
        while (rs.next()) {
          nd = new NguoiDung(rs.getInt("id"), rs.getString("tendangnhap"), rs.getString("loaiquyen"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return nd;
  }
}
