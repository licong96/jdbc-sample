package com.imooc.jdbc.hrapp.command;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.imooc.jdbc.common.DBUtils;
import com.imooc.jdbc.hrapp.entity.Employee;

/**
 * 分页查询员工数据
 */
public class PaginationCommand implements Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入页码：");
        int page = in.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Employee> list = new ArrayList();

        try {
            conn = DBUtils.getConnection();
            String sql = "select * from employee limit ?, 10";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (page - 1) * 10);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer eno = rs.getInt("eno");
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                Date hiredate = rs.getDate("hiredate");
                Employee emp = new Employee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);
                emp.setHireDate(hiredate);
                list.add(emp);
            }
            System.out.println("一共查询到" + list.size() + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(rs, pstmt, conn);
        }

    }
}
