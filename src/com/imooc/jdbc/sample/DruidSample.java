package com.imooc.jdbc.sample;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * Druid连接池示例
 */
public class DruidSample {
  public static void main(String[] args) {
    // 1. 加载属性文件
    Properties properties = new Properties();
    String propertyFile = DruidSample.class.getResource("/druid-filter.properties").getPath();
    try {
      // 空格会被转义成%20，需要解码，还原成空格
      propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
      properties.load(new FileInputStream(propertyFile));
    } catch (Exception e) {
      e.printStackTrace();
    }
    Connection conn = null;
    try {
      // 2. 获取DataSource数据源对象
      DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
      // 3. 创建数据库连接对象
      conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from employee where dname='研发部'");

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
        conn.close(); // 回收连接池
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
