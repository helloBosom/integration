package fun.peri.sql.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    String pathname;

    public Connect(String pathname) {
        this.pathname = pathname;
    }

    public Connect() {
    }

    private String URL = JDBCProperties.getProperties(pathname).getProperty("url");
    private String USERNAME = JDBCProperties.getProperties(pathname).getProperty("username");
    private String PASSWORD = JDBCProperties.getProperties(pathname).getProperty("password");

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
