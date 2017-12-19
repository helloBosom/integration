package com.logic.dao.dbhelp;

import java.sql.*;

import org.junit.Test;

public class DBHelpUtil {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String CONN = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USERNAME = "scott";
    private static final String PWD = "lv";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(CONN, USERNAME, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Test
    public void Test() {
        Connection connection = getConnection();
        System.out.println(connection);
    }

    public static boolean executeSingleSql(String sql) {
        boolean flag = false;
        Statement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.createStatement();
            int count = st.executeUpdate(sql);
            if (count > 0)
                flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(null, st, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static void closeAll(ResultSet rs, Statement st, Connection connection) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
