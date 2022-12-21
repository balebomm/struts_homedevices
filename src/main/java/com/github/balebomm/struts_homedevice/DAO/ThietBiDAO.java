package com.github.balebomm.struts_homedevice.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.balebomm.struts_homedevice.Models.ThietBi;

public class ThietBiDAO {
  public List<ThietBi> list() {
    List<ThietBi> listthietbi = new ArrayList<ThietBi>();
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("SELECT * FROM thietbi");
      ResultSet rs = db.executeQuery(stm);
      if (rs != null) {
        while (rs.next()) {
          ThietBi thietbi = new ThietBi(Integer.parseInt(rs.getString("id")), rs.getString("tenthietbi"), rs.getString("trangthai"));
          listthietbi.add(thietbi);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return listthietbi;
  }

  public boolean add(ThietBi thietbi) {
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("INSERT INTO thietbi(tenthietbi,trangthai) VALUES(?,?)");
      stm.setString(1, thietbi.getTenthietbi());
      stm.setString(2, thietbi.getTrangthai());
      db.executeUpdate(stm);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
