package com.imooc.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSample {
  public static void main(String[] args) {
    try {
      // 1. 加载并注册JDBC驱动
      Class.forName("com.mysql.cj.jdbc.Driver");
      // 2. 创建数据库连接
      String url = "jdbc:mysql://localhost:3306/imooc_java?useSSL=false&useUnicode=true&characterEncoding=UTF-8&severTimezone=Aisa/Shanghai&allowPublicKeyRetrieval=true";
      String username = "root";
      String password = "123456";
      Connection conn = DriverManager.getConnection(url, username, password);
      // 3. 创建Statement对象
      // 4. 遍历查询结果
      // 5. 关闭连接，释放资源

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
