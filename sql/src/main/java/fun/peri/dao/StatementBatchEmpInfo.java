package fun.peri.dao;

import fun.peri.dao.dbhelp.DBHelp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementBatchEmpInfo {

	public static void main(String[] args) {
		addBatchUserInfo();
	}

	public static void addBatchUserInfo() {
		Connection connection = null;
		Statement st = null;
		try {
			connection = DBHelp.getConnection();
			st = connection.createStatement();
			connection.setAutoCommit(false);
			String sql1 = " insert into dept(deptno,dname,loc) values(50,'af','dd' ) ";
			st.addBatch(sql1);
			String sql2 = " insert into dept(deptno,dname,loc) values(60,'af','dsd' ) ";
			st.addBatch(sql2);
			String sql3 = " insert into dept(deptno,dname,loc) values(70,'afcs','s' ) ";
			st.addBatch(sql3);
			st.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
