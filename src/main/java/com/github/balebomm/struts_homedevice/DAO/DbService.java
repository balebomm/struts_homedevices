package com.github.balebomm.struts_homedevice.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbService {
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/homedevices?allowPublicKeyRetrieval=true&useSSL=false";
  static final String USER = "root";
  static final String PASS = "password";

  Connection conn = null;
  public DbService() {
    try {
      Class.forName(JDBC_DRIVER);
      this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
  public Connection getConn() {
    return conn;
  }

  public ResultSet executeQuery(PreparedStatement stmt) {
    ResultSet rs = null;
    try {
      rs = stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return rs;
  }

  public void executeUpdate(PreparedStatement stmt) {
    try {
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
