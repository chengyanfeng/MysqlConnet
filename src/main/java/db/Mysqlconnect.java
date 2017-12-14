package db;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
@EnableAutoConfiguration
public class Mysqlconnect {
    public static final String url = "jdbc:mysql://127.0.0.1/test";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "root";
    public Connection conn = null;
    public PreparedStatement pst = null;

    public Mysqlconnect(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);//准备执行语句
            //每次存100条数据返回一次
            pst.setFetchSize(100);
            //mysql 的时候可以用这个方法，但是orcle好像还不行
             pst.setFetchSize(Integer.MIN_VALUE);
            pst.setFetchDirection(ResultSet.FETCH_REVERSE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
