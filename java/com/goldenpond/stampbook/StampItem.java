package com.goldenpond.stampbook;

import java.util.Date;

public class StampItem {

	private long id;

	private String issueNumber;

	private String name;

	private Date issueDate;

	private String designedBy;

	private String printedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getDesignedBy() {
		return designedBy;
	}

	public void setDesignedBy(String designedBy) {
		this.designedBy = designedBy;
	}

	public String getPrintedBy() {
		return printedBy;
	}

	public void setPrintedBy(String printedBy) {
		this.printedBy = printedBy;
	}

	@Override
	public String toString() {
		return "StampItem [issueNumber=" + issueNumber + ", name=" + name
				+ ", issueDate=" + issueDate + ", designedBy=" + designedBy
				+ ", printedBy=" + printedBy + "]";
	}
}
