package com.imooc.jdbc.hrapp;

import java.util.Scanner;

import com.imooc.jdbc.hrapp.command.*;

public class HumanResourceApplication {
  public static void main(String[] args) {
    System.out.println("1-查询部门员工");
    System.out.println("2-办理员工入职");
    System.out.println("3-调整员工薪资");
    System.out.println("4-员工离职");
    System.out.println("5-分页查询员工数据");
    System.out.println("请选择功能：");
    Scanner in = new Scanner(System.in);
    Integer cmd = in.nextInt();

    Command command = null;
    switch (cmd) {
      case 1:
        // 查询部门员工
        command = new PstmtQueryCommand();
        command.execute();
        break;
      case 2:
        // 办理员工入职
        command = new InsertCommand();
        command.execute();
        break;
      case 3:
        // 调整员工薪资
        command = new UpdateCommand();
        command.execute();
        break;
      case 4:
        // 员工离职
        command = new DeleteCommand();
        command.execute();
        break;
      case 5:
        // 分页查询员工数据
        command = new PaginationCommand();
        command.execute();
        break;
    }
    in.close();
  }
}
