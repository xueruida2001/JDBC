package 模拟登录;

import java.sql.*;
import java.util.Scanner;

public class 模拟登录_解决SQL注入问题 {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql:///模拟登录";//这个最后的模拟登录是数据库的名字，即create database的名字
        String user = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);
        //获取登录对象
        Scanner sc=new Scanner(System.in);
        System.out.print("please input your userName:");
        String userName=sc.next();
        System.out.println("please input your userPassword");
        String userPassword=sc.next();
        // SQL 中的参数 先⽤ ？占位表示
        String sql = "select * from t_user where name=? and passwd=?";
        // 获取 PreparedStatement 对象
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        // 设置参数值
        prepareStatement.setString(1, userName);
        prepareStatement.setString(2, userPassword);
    // 执⾏ SQL
        ResultSet resultSet = prepareStatement.executeQuery();
    // 判断匹配结果
        if (resultSet.next()) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败！");
        }
        // 释放资源
        resultSet.close();
        prepareStatement.close();
    }
}
