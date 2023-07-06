package com.imooc.jdbc.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.imooc.jdbc.common.DBUtils;

/**
 * 数据批量处理
 */
public class BatchSample {
  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = DBUtils.getConnection();
      // 关闭自动提交，开启事务
      conn.setAutoCommit(false);
      String sql = "insert into employee(eno, ename, salary, dname) values(?,?,?,?)";
      pstmt = conn.prepareStatement(sql);
      for (int i = 100; i < 200; i++) {
        pstmt.setInt(1, i);
        pstmt.setString(2, "员工" + i);
        pstmt.setFloat(3, 4000);
        pstmt.setString(4, "市场部");
        pstmt.addBatch();
      }
      pstmt.executeBatch();
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
