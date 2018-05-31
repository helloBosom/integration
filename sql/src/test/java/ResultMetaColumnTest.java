import com.logic.dao.dbhelp.DBHelp;

import java.sql.*;

public class ResultMetaColumnTest {
    public void testMethod1() {
        String sql = "SELECT empno,ename,d.deptno,d.name FROM dept d, emp e WHERE d.deptno =e.deptno";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBHelp.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            /**
             * public interface ResultSetMetaData可用于获取关于 ResultSet
             * 对象中列的类型和属性信息的对象。以下代码片段创建 ResultSet 对象 rs，创建 ResultSetMetaData 对象
             * rsmd，并使用 rsmd 查找 rs 有多少列，以及 rs 中的第一列是否可以在 WHERE 子句中使用。
             * ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM TABLE2");
             * ResultSetMetaData rsmd = rs.getMetaData();
             * int numberOfColumns = rsmd.getColumnCount();
             *  boolean b = rsmd.isSearchable(1);
             */
            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println(metaData.getColumnCount());
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnName(i) + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
