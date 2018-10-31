package fun.peri.dao;

import fun.peri.dao.dbhelp.DBHelp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallProcedureDept {

    public static void main(String[] args) {
        executeProcedureInfo();
    }

    public static void executeProcedure() {
        CallableStatement callable = null;
        Connection connection = null;
        try {
            connection = DBHelp.getConnection();
            callable = connection.prepareCall("{call p_insert_dept(?,?,?)}");
            callable.setInt(1, 50);
            callable.setString(2, "sales");
            callable.setString(3, "xian");
            callable.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelp.closeAll(null, callable, connection);
            System.out.println("over");
        }
    }

    public static void executeProcedureInfo() {
        CallableStatement callable = null;
        Connection connection = null;
        try {
            connection = DBHelp.getConnection();
            callable = connection.prepareCall("{call p_count_emp(?)}");
            callable.registerOutParameter(1, Types.INTEGER);
            callable.execute();
            int result = callable.getInt(1);
            System.out.println("result:" + result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelp.closeAll(null, callable, connection);
        }
    }
}
