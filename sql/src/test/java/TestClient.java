import com.logic.dao.dbhelp.DBHelp;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestClient {
    @Test
    public void testMethod1() {

        List<String> sqls = new ArrayList<String>();
        String sql = "update dept set dname = 'software' where deptno = 40";
        sqls.add(sql);
        sql = "insert into dept(deptno,dname,loc) values(50,'sales','xian')";
        sqls.add(sql);
        DBHelp.executeBatchSql(sqls.toArray());
        System.out.println("success");
    }

    @Test
    public void testMethod2() {
        String sql = "update emp set ename =? ,job=?,sal=? where empno=?";
        List<Object> parameters = new ArrayList<Object>();
        parameters.add("KITTY");
        parameters.add("MANAGER");
        parameters.add(3000);
        parameters.add(7934);
        boolean flag = DBHelp.executeSingleSql(sql, parameters.toArray());
        if (flag) {
            System.out.println("success");
        } else {
            System.out.println("failure");
        }
    }

    @Test
    public void testMethod3() {
        String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
        List<List> parameters = new ArrayList<List>();
        List parameter1 = new ArrayList();
        parameter1.add(1);
        parameter1.add("cissst1");
        parameter1.add("xian1");
        List parameter2 = new ArrayList();
        parameter2.add(2);
        parameter2.add("cissst2");
        parameter2.add("xian2");
        parameters.add(parameter1);
        parameters.add(parameter2);
        DBHelp.executeBatchSql(sql, parameters);
        System.out.println("over");
    }

    @Test
    public void testMethod4() {
        String sql = "select count(*) from emp";
        int rows = (Integer.parseInt(DBHelp.executeSqlReturnValue(sql).toString()));
        System.out.println("rows:" + rows);
    }

    @Test
    public void testMethod5() {
        int currentPage = 2;
        int pageSize = 5;
        String sql = "SELECT m.empno,m.ename,m.job,m.mgr,m.hiredate,m.sal,m.comm,m.deptno FROM "
                + "(SELECT rownum r ,empno,ename,job,mgr,hiredate,sal,comm,deptno " + "FROM emp WHERE rownum<="
                + currentPage * pageSize + ") m WHERE m.r>=" + ((currentPage - 1) * pageSize + 1);

        PreparedStatement ps;
        try {
            Connection connection = DBHelp.getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                System.out.println("empno:" + empno + " " + "ename:" + ename + " " + "job:" + job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMethod6() {
        String sql = "select empno,ename,d.deptno,d.dname from dept d,emp e where d.deptno = e.deptno";
        List<List> rows = DBHelp.queryRowsMultiTables(sql);
        if (rows != null && rows.size() > 0) {
            for (int i = 0; i < rows.size(); i++) {
                List record = rows.get(i);
                for (int j = 0; j < record.size(); j++) {
                    System.out.print(record.get(j) + " ");
                }
                System.out.println();
            }
        }
    }
}
