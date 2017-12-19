package com.logic.dao.dbhelp;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DBHelp {
    private static BasicDataSource basicDataSource = new BasicDataSource();

    static {
        basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        basicDataSource.setUsername("scott");
        basicDataSource.setPassword("lv");
        basicDataSource.setMaxActive(10);
        basicDataSource.setMaxWait(5000);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMethod1() {
        try {
            Connection connection = getConnection();
            System.out.println(connection.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
