package com.imooc.jdbc.sample;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.hrapp.entity.Employee;

/**
 * Apache Commons DBUtils 示例
 */
public class DBUtilsSample {
    public static void main(String[] args) {
        query();
    }
    private static void query() {
        Properties properties = new Properties();
        String propertyFile = DruidSample.class.getResource("/druid-filter.properties").getPath();
        try {
            // 空格会被转义成%20，需要解码，还原成空格
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 利用 Apache Commons DBUtils 简化 JDBC 操作
            QueryRunner queryRunner = new QueryRunner(dataSource);
            List<Employee> list = queryRunner.query("select * from employee limit ?,10",
                    new BeanListHandler<Employee>(Employee.class),
                    new Object[]{10});
            // 输出结果
            for (Employee emp : list) {
                System.out.println(emp.getEno() + ", " + emp.getEname() + ", " + emp.getSalary() + ", " + emp.getDname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
