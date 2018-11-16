package fun.peri.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBHelp {

    private static Properties props = new Properties();
    private static BasicDataSource basicDataSource = new BasicDataSource();

    static {
        ClassLoader loader = DBHelp.class.getClassLoader();
        InputStream ins = loader.getResourceAsStream("dbConfig.properties");
        try {
            props.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
        basicDataSource.setDriverClassName(props.getProperty("driver"));
        basicDataSource.setUrl(props.getProperty("url"));
        basicDataSource.setUsername(props.getProperty("username"));
        basicDataSource.setPassword(props.getProperty("password"));
        basicDataSource.setMaxActive(new Integer(props.getProperty("maxActive")));
        basicDataSource.setMaxWait(new Integer(props.getProperty("maxWait")));
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
//            Class.forName(props.getProperty("driver"));
//            connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }
        return connection;
    }

    @Test
    public void testMethod1() {
        Connection connection;
        connection = getConnection();
        System.out.println(connection.toString());
    }

    public static boolean executeSingleSql(String sql) {
        boolean flag = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            st = conn.createStatement();
            int count = st.executeUpdate(sql);
            if (count > 0) {
                flag = true;
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            closeAll(null, st, conn);
        }
        return flag;
    }

//    @Test
//    public void testMethod2() {
//        List<String> sqls = new ArrayList<String>();
//        String sql = "insert into dept(deptno,dname,loc) values (50,'software','xian')";
//        sqls.add(sql);
//        sql = "insert into emp(empno,ename) values ('1002','admin')";
//        sqls.add(sql);
//        executeBatchSql(sqls.toArray());
//        System.out.println("over");
//    }

    public static void executeBatchSql(Object... sqls) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            if (sqls != null && sqls.length > 0) {
                for (Object object : sqls) {
                    // addBatch:将给定的 SQL 命令添加到此 Statement 对象的当前命令列表中。
                    statement.addBatch(object.toString());
                }
                /*
                 * executeBatch() 将一批命令提交给数据库来执行，如果全部 命令执行成功，则返回更新计数组成的数组。
                 */
                statement.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            closeAll(null, statement, connection);
        }
    }

//    @Test
//    public void testMethod3() {
//        List parameters = new ArrayList();
//        String sql = "insert into dept(deptno,dname,loc) values (?,?,?)";
//        parameters.add(60);
//        parameters.add("work");
//        parameters.add("beijing");
//
//        boolean flag = executeSingleSql(sql, parameters.toArray());
//        System.out.println(flag);
//    }

    public static boolean executeSingleSql(String sql, Object... parameters) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            if (parameters != null && parameters.length > 0) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setObject(i + 1, parameters[i]);
                }
            }
            int count = ps.executeUpdate();
            if (count > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, connection);
        }
        return flag;
    }

    public static void executeBatchSql(String sql, List<List> parameters) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            if (parameters != null && parameters.size() > 0) {
                for (int i = 0; i < parameters.size(); i++) {
                    List parameter = parameters.get(i);
                    for (int j = 0; j < parameter.size(); j++) {
                        Object object = parameter.get(j);
                        ps.setObject(j + 1, object);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            closeAll(null, ps, connection);
        }
    }

    public static Object executeSqlReturnValue(String sql) {
        Object object = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                /*
                 * statement接口 getObject(int columnIndex) 方法 以 Java 编程语言中 Object
                 * 的形式获取此 ResultSet 对象的当前行中指定列的值。
                 */
                object = rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, connection);
        }
        return object;
    }

//    @Test
//    public void testMethod5() {
//        String sql = "select empno,ename,d.deptno,d.dname from dept d,emp e " + " where d.deptno = e.deptno";
//        List<List> rows = queryRowsMultiTables(sql);
//        for (int i = 0; i < rows.size(); i++) {
//            List row = rows.get(i);
//            for (int j = 0; j < row.size(); j++) {
//                Object obj = row.get(j);
//                System.out.print(obj.toString() + "  ");
//            }
//            System.out.println();
//        }
//    }

    public static List<List> queryRowsMultiTables(String sql) {
        List<List> resultRows = new ArrayList<List>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            List row;
            while (rs.next()) {
                row = new ArrayList();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    row.add(rs.getObject(metaData.getColumnName(i + 1)));
                }
                resultRows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, connection);
        }
        return resultRows;
    }

    public static void closeAll(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
