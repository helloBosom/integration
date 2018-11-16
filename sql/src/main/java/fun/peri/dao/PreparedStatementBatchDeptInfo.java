package fun.peri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementBatchDeptInfo {

    public static void main(String[] args) {
        deptInsert();
    }

    public static void deptInsert() {
        Connection connection = DBHelp.getConnection();
        String sql = "insert into dept(deptno,dname,loc) values (?,?,?)";
        /*
         * public interface PreparedStatementextends Statement表示预编译的 SQL 语句的对象。
         * SQL 语句被预编译并且存储在 PreparedStatement 对象中。然后可以使用此对象高效地多次执行该语句。
         * 注：用来设置 IN 参数值的 setter 方法（setShort、setString 等等）必须指定与输入参数的已定义 SQL
         * 类型兼容的类型。例如，如果 IN 参数具有 SQL 类型 INTEGER，那么应该使用 setInt 方法。
         * 如果需要任意参数类型转换，使用 setObject 方法时应该将目标 SQL 类型作为其参数的类型。
         */
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 4; i++) {
                ps.setInt(1, i);
                ps.setString(2, "cissst" + i);
                ps.setString(3, "ds" + i);
                /*
                 * addBatch void addBatch() throws SQLException将一组参数添加到此
                 * PreparedStatement 对象的批处理命令中
                 */
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBHelp.closeAll(null, ps, connection);
        }
    }
}
