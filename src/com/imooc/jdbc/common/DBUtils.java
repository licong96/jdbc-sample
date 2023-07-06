package com.imooc.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBUtils {
  /**
   * 创建数据库连接
   * @return 新的Connection对象
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    // 1. 加载并注册JDBC驱动
      Class.forName("com.mysql.cj.jdbc.Driver");

      // 2. 创建数据库连接
      String dbURL = "jdbc:mysql://localhost:3306/imooc_java?useSSL=false&useUnicode=true&characterEncoding=UTF-8&severTimezone=Aisa/Shanghai&allowPublicKeyRetrieval=true";
      String dbUsername = "root";
      String dbPassword = "123456";
      Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    return conn;
  }

  /**
   * 关闭数据库连接，释放资源
   * @param rs 结果集对象
   * @param stmt Statement对象
   * @param conn Connection对象
   */
  public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (stmt != null) {
        stmt.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (conn != null && !conn.isClosed()) {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
