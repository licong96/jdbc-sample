package com.imooc.jdbc.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.imooc.jdbc.common.DBUtils;

/**
 * 事务控制示例
 */
public class TransactionSample {
  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = DBUtils.getConnection();
      // 关闭自动提交，开启事务
      conn.setAutoCommit(false);
      String sql = "insert into employee(eno, ename, salary, dname) values(?,?,?,?)";
      for (int i = 100; i < 200; i++) {
        // if (i == 105) {
        //   throw new RuntimeException("插入失败");
        // }
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, i);
        pstmt.setString(2, "员工" + i);
        pstmt.setFloat(3, 4000);
        pstmt.setString(4, "市场部");
        pstmt.executeUpdate();
      }
      // 提交事务
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
      // 回滚事务
      try {
        if (conn != null && !conn.isClosed()) {
          conn.rollback();
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } finally {
      DBUtils.closeConnection(null, pstmt, conn);
    }

  }
}
