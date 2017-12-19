package com.logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test3 {

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "lv");
		PreparedStatement ps = conn.prepareStatement("insert into dept values(? , ? , ?)");
		ps.setInt(1, 21);
		ps.setString(2, "s1");
		ps.setString(3, "s2");
		ps.addBatch();
		ps.setInt(1, 21);
		ps.setString(2, "s3");
		ps.setString(3, "s4");
		ps.addBatch();
		ps.setInt(1, 21);
		ps.setString(2, "s5");
		ps.setString(3, "s6");
		ps.executeBatch();
		ps.close();
		conn.close();
	}
}
