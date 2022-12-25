package com.github.balebomm.struts_homedevice.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.balebomm.struts_homedevice.Models.DuLieuThietBi;

public class DuLieuThietBiDAO {
  public DuLieuThietBi get(int id) {
    DuLieuThietBi dulieuthietbi = null;
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("SELECT * FROM dulieuthietbi WHERE id = ?");
      stm.setInt(1, id);
      ResultSet rs = db.executeQuery(stm);
      if (rs != null) {
        while (rs != null) {
          dulieuthietbi = new DuLieuThietBi(rs.getInt("idthietbi"), rs.getFloat("giatri"), rs.getString("ngaytao"));
        }
      }
    } catch (SQLException e) {
      // Do nothing
    }

    return dulieuthietbi;
  }

  public List<DuLieuThietBi> list(int idthietbi, String fromdate, String todate) {
    List<DuLieuThietBi> listdulieuthietbi = new ArrayList<DuLieuThietBi>();
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("SELECT * FROM dulieuthietbi WHERE idthietbi = ? AND ngaytao BETWEEN ? AND ?");
      stm.setInt(1, idthietbi);
      stm.setString(2, fromdate + " 00:00:00");
      stm.setString(3, todate + " 23:59:59");
      ResultSet rs = db.executeQuery(stm);
      if (rs != null) {
        while (rs.next()) {
          DuLieuThietBi dulieuthietbi = new DuLieuThietBi(rs.getInt("id"), rs.getInt("idthietbi"), rs.getFloat("giatri"), rs.getString("ngaytao"));
          listdulieuthietbi.add(dulieuthietbi);
        }
      }
    } catch (SQLException e) {
      // Do nothing
      e.printStackTrace();
    }

    return listdulieuthietbi;
  }
}
