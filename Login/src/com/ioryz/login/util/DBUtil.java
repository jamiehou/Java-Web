package com.ioryz.login.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ioryz.login.exception.DBException;

public class DBUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test";
            conn = DriverManager.getConnection(url, "root", "welcome123_");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }
}
