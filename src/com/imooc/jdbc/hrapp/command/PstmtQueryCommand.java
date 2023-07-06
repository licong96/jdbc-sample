package com.imooc.jdbc.hrapp.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PstmtQueryCommand implements Command {
  @Override
  public void execute() {
    System.out.println("请输入部门名称：");
    Scanner in = new Scanner(System.in);
    String pdname = in.nextLine();
    //
    Connection conn = null;
    try {
      // 1. 加载并注册JDBC驱动
      Class.forName("com.mysql.cj.jdbc.Driver");

      // 2. 创建数据库连接
      String dbURL = "jdbc:mysql://localhost:3306/imooc_java?useSSL=false&useUnicode=true&characterEncoding=UTF-8&severTimezone=Aisa/Shanghai&allowPublicKeyRetrieval=true";
      String dbUsername = "root";
      String dbPassword = "123456";
      conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

      // 3. 创建prepareStatement
      String sql = "select * from employee where dname=?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      // 赋值
      pstmt.setString(1, pdname);
      // 结果集
      ResultSet rs = pstmt.executeQuery();

      // 4. 遍历查询结果
      while (rs.next()) {
        Integer eno = rs.getInt(1);
        String ename = rs.getString("ename");
        Float salary = rs.getFloat("salary");
        String dname = rs.getString("dname");
        System.out.println(eno + ", " + ename + ", " + salary + ", " + dname);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null && !conn.isClosed()) {
          // 5. 关闭连接，释放资源
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
