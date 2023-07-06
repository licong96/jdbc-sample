package com.imooc.jdbc.hrapp.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.imooc.jdbc.common.DBUtils;

public class InsertCommand implements Command {
  @Override
  public void execute() {
    System.out.println("InsertCommand.execute");
    Scanner in = new Scanner(System.in);
    System.out.println("请输入员工编号：");
    int eno = in.nextInt();
    System.out.println("请输入员工姓名：");
    String ename = in.next();
    System.out.println("请输入员工薪资：");
    float salary = in.nextFloat();
    System.out.println("请输入员工所在部门：");
    String dname = in.next();
    System.out.println("请输入员工入职日期");
    String strHiredate = in.next();
    // 将输入的字符串类型的日期转换成java.sql.Date类型，分为两步
    // 1.将日期字符串转换成java.util.Date类型
    java.util.Date udHiredata = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      udHiredata = sdf.parse(strHiredate);
    } catch (Exception e) {
      System.out.println("您输入的日期格式有误，格式为：yyyy-MM-dd");
    }
    // 2.将java.util.Date类型转换成java.sql.Date类型
    long time = udHiredata.getTime();
    java.sql.Date sdHiredate = new java.sql.Date(time);

    // 建立数据库连接
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = DBUtils.getConnection();
      String sql = "insert into employee(eno, ename, salary, dname, hiredate) values(?, ?, ?, ?, ?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, eno);
      pstmt.setString(2, ename);
      pstmt.setFloat(3, salary);
      pstmt.setString(4, dname);
      pstmt.setDate(5, sdHiredate);
      int cnt = pstmt.executeUpdate();
      System.out.println("cnt: " + cnt);
      System.out.println("员工入职手续已办理！");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtils.closeConnection(null, pstmt, conn);
    }
  }
}
