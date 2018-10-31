import fun.peri.sql.connect.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        Connect connect = new Connect("");
        Connection connection = connect.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.afterLast();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
