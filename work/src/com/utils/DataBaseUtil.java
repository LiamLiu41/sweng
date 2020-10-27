package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {

    //�������ݿ�
    public static Connection getConn() {
        Connection conn = null;
        try {
            //�������ݿ�����
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/test?user=dbuser&password=123456&useUnicode=true&characterEncoding=UTF-8";
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println(1123);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //�ر����ݿ�
    public static void closeConn(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

