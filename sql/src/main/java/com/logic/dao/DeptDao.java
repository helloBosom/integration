package com.logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeptDao {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		Connection conn = DriverManager.getConnection(url, "scott", "lv");
		System.out.println(conn.toString());
		System.out.println("部门编号:");
		int deptno = scan.nextInt();
		System.out.println("部门名称:");
		String deptName = scan.next();
		System.out.println("地址ַ:");
		String deptLoc = scan.next();
		String sql = "insert into dept(deptno,dname,loc) values" + "(" + deptno + ",'" + deptName + "','" + deptLoc
				+ "')";
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		if (result > 0)
			System.out.println("insert success");
		else
			System.out.println("insert failure");

	}

}
