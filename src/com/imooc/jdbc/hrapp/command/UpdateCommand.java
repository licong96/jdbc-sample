package com.imooc.jdbc.hrapp.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.imooc.jdbc.common.DBUtils;

public class UpdateCommand implements Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int eno = in.nextInt();
        System.out.println("请输入员工新的薪资");
        float salary = in.nextFloat();

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update employee set salary = ? where eno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, salary);
            pstmt.setInt(2, eno);
            int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                System.out.println("员工薪资调整完毕");
            } else {
                System.out.println("查无此人");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(null, pstmt, conn);
            in.close();
        }

    }
}
