package com.goldenpond.stampbook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StampItemDao extends Dao{

	public void create(StampItem item) {
		String sql = "insert into STAMP_ITEM(ISSUE_NUMBER, NAME, ISSUE_DATE, DESIGNED_BY, PRINTED_BY) " +
				"VALUES (" + item.getIssueNumber() + ", " + item.getName() + ", " + item.getIssueDate() + ", " + item.getDesignedBy() + ", " + item.getPrintedBy() + ")";
		int rowCnt = executeUpdate(sql);
	}

	public StampItem fetch(String issueNumber) {
		String sql = "select ISSUE_NUMBER, NAME, ISSUE_DATE, DESIGNED_BY, PRINTED_BY from STAMP_ITEM where ISSUE_NUMBER='" + issueNumber + "'";
		StampItem selected = new StampItem();
		ResultSet rs = executeQuery(sql);
		try {
			while (rs.next()) {
				selected.setIssueNumber(rs.getString("ISSUE_NUMBER"));
				selected.setName(rs.getString("NAME"));
				selected.setIssueDate(rs.getDate("ISSUE_DATE"));
				selected.setDesignedBy(rs.getString("DESIGNED_BY"));
				selected.setPrintedBy(rs.getString("PRINTED_BY"));
			}
			return selected;
		} catch (SQLException e) {
			System.out.println("failed to get data from result set");
			throw new RuntimeException(e);
		}
	}

	public void update(StampItem item) {
		String sql = "update STAMP_ITEM set ISSUE_NUMBER='" + item.getIssueNumber() 
				+ "', NAME='" + item.getName() + "', ISSUE_DATE=" + item.getIssueDate() 
				+ " where ISSUE_NUMBER='" + item.getIssueNumber() + "'";
		int rowCnt = executeUpdate(sql);
		System.out.println(rowCnt + " row(s) were updated");
	}

	public void delete(StampItem item) {
		String sql = "delete from STAMP_ITEM where ISSUE_NUMBER='" + item.getIssueNumber() + "'";
		int rowCnt = executeUpdate(sql);
		System.out.println(rowCnt + " row(s) were deleted");
	}
}
