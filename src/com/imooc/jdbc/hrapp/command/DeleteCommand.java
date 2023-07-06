package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteCommand implements Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int eno = in.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from employee where eno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eno);
            int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                System.out.println("员工离职手续已完成");
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
