package 模拟登录;

import com.mysql.cj.xdevapi.SqlResult;

import java.sql.*;
import java.util.Scanner;

public class 模拟登录 {
    public static void main(String[] args) throws SQLException {
        String url="jdbc:mysql:///模拟登录";//这个最后的模拟登录是数据库的名字，即create database的名字
        String user="root";
        String password="123456";
        Connection connection= DriverManager.getConnection(url,user,password);
        //获取登录对象
        Scanner sc=new Scanner(System.in);
        System.out.print("please input your userName:");
        String userName=sc.next();
        System.out.println("please input your userPassword");
        String userPassword=sc.next();
        /* 定义 SQL 语句
        为防止SQL注入，传参数有两种方式
        #{}采用预编译一方是，将传参部分用？代替，防止SQL注入
        ${}传入参数，但是存在SQL注入漏洞
        这个t_user是数据库中数据表table的名称
        */
        //注意表里写的是name和passwd，所以select里面写的是passwd，而不是password，否则会报错
        //这种方法可能导致SQL注入问题
        String sql = "select * from t_user where name='" + userName + "' and passwd='" + userPassword + "'";
        // 获取执⾏ SQL 的对象
        Statement statement=connection.createStatement();
        //结果查询
        ResultSet resultSet =statement.executeQuery(sql);
        // 处理结果 遍历查询结果集
        if (resultSet.next())
            System.out.println("登录成功");
        else
            System.out.println("查询失败");
        //释放资源。释放的顺序应和上面创建的顺序相反
        resultSet.close();//resultSet是最后写的，释放的时候应该最先释放
        statement.close();
        connection.close();//connection是最先写的，应该最后释放
    }
}
