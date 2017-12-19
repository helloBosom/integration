package com.logic.dao;

import com.logic.dao.dbhelp.DBHelpUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedSqlInject {

	public static void main(String[] args) {
		if (validateUser())
			System.out.println("seccess");
		else
			System.out.println("failure");
	}

	public static boolean validateUser() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean flag = false;
		System.out.println("请输入用户名:");
		String username = scan.next();
		System.out.println("请输入密码:");
		String userpwd = scan.next();
		Connection connection = DBHelpUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from emp where empno = ? and ename = ?";
			st = connection.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, userpwd);
			rs = st.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0)
					flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBHelpUtil.closeAll(rs, st, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
