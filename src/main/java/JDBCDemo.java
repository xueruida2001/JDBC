import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        // 注册驱动
        //Class.forName("com.mysql.jdbc.Driver");
        // 获取连接
        /*方法1：如果是连接本机数据库，且端⼝为默认值 3306，则localhost:3306可以用1跟/代替
        即略写为：jdbc:mysql:///数据库名称?键值对参数(注意是3根横线)
        */
        String url = "jdbc:mysql:///db1";
        //方法2：常规写法
        //String url = "jdbc:mysql://localhost:3306/db1";
        String user = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, user, password);
        // 定义 SQL 语句
        String sql = "update t_cartoons set name='那撸多' where id='102'";
        // 获取执⾏ SQL 的对象
        Statement statement = conn.createStatement();
        // 执⾏ SQL
        int updateCount = statement.executeUpdate(sql); // 返回的结果 表示受影响⾏数
        // 处理结果
        System.out.println("受影响的⾏数：" + updateCount);
        // 释放资源
        statement.close();
        conn.close();
    }
}
