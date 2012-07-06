package com.goldenpond.stampbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao {

	private Connection conn;
	private Statement stmt;

	protected int executeUpdate(String sql) {
		try {
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("failed to execute the given sql [" + sql + "]");
			throw new RuntimeException(e);
		}
	}

	protected ResultSet executeQuery(String sql) {
		try {
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("failed to execute the given sql [" + sql + "]");
			throw new RuntimeException(e);
		}
	}

	public void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stampbook", "root", "root");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("failed to connect to database");
			throw new RuntimeException(e);
		}
	}

	public void disconnect() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("failed to disconnect with database");
			throw new RuntimeException(e);
		}
	}

}
