package com.github.balebomm.struts_homedevice.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.balebomm.struts_homedevice.Models.ThietBi;

public class ThietBiDAO {
  public ThietBi get(int id) {
    ThietBi thietbi = null;
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("SELECT * FROM thietbi WHERE id = ?");
      stm.setInt(1, id);
      ResultSet rs = db.executeQuery(stm);
      if (rs != null) {
        while (rs.next()) {
          thietbi = new ThietBi(rs.getString("tenthietbi"), rs.getString("trangthai"));
        }
      }
    } catch (SQLException e){
      // Do nothing
    }

    return thietbi;
  }

  public List<ThietBi> list() {
    List<ThietBi> listthietbi = new ArrayList<ThietBi>();
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("SELECT * FROM thietbi");
      ResultSet rs = db.executeQuery(stm);
      if (rs != null) {
        while (rs.next()) {
          ThietBi thietbi = new ThietBi(rs.getInt("id"), rs.getString("tenthietbi"), rs.getString("trangthai"));
          listthietbi.add(thietbi);
        }
      }
    } catch (SQLException e) {
      // Do nothing
    }

    return listthietbi;
  }

  public boolean add(ThietBi thietbi) {
    boolean ok = true;
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("INSERT INTO thietbi(tenthietbi,trangthai) VALUES(?,?)");
      stm.setString(1, thietbi.getTenthietbi());
      stm.setString(2, thietbi.getTrangthai());
      db.executeUpdate(stm);
    } catch (SQLException e) {
      // Do nothing
      ok = false;
    }

    return ok;
  }

  public boolean update(ThietBi thietbi) {
    boolean ok = true;
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("UPDATE thietbi SET tenthietbi=?, trangthai=? WHERE id=?");
      stm.setString(1, thietbi.getTenthietbi());
      stm.setString(2, thietbi.getTrangthai());
      stm.setInt(3, thietbi.getId());
      db.executeUpdate(stm);
    } catch (SQLException e) {
      ok = false;
    }

    return ok;
  }

  public boolean delete(int id) {
    boolean ok = true;
    DbService db = new DbService();
    PreparedStatement stm;
    try {
      stm = db.getConn().prepareStatement("DELETE FROM thietbi WHERE id = ?");
      stm.setInt(1, id);
      db.executeUpdate(stm);
    } catch (SQLException e) {
      ok = false;
    }

    return ok;
  }
}
