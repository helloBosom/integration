import java.sql.*;

public class Test1 {
    private static String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static String user = "scott";
    private static String password = "lv";

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = null;
        Statement st = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            System.out.println(conn.toString());
            st = conn.createStatement();
            st.addBatch("delete from dept where deptno='60'");
            st.addBatch("insert into emp(empno,ename) values ('1015','zhang')");
            int[] result = st.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(null, st, conn);
        }
    }

    public static void closeAll(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Test2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            System.out.println(conn.toString());
            ps = conn.prepareStatement("INSERT into dept values (?, ?, ?)");
            for (int n = 0; n < 10; n++) {
                ps.setString(1, n + "");
                ps.setString(2, "cissst" + n);
                ps.setString(3, "orcl" + n);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (Exception e) {
            try {
                /*
                 * void rollback() 取消在当前事务中进行的所有更改，并释放此 Connection
                 * 对象当前保存的所有数据库锁定。
                 */
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            closeAll(null, ps, conn);
        }
    }
}