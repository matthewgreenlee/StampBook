package com.goldenpond.stampbook.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STAMP")
@XmlRootElement
public class Stamp {

	private long id;

	private String issueNumber;

	private String name;

	private Date issueDate;

	private String designedBy;

	private String printedBy;

	private List<StampItem> items;

	public Stamp() {
		super();
		items = new ArrayList<StampItem>();
	}

	@Id
	@Column(name = "STAMP_ID")
	@GeneratedValue(generator="native")
	@GenericGenerator(name="native", strategy = "native")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "ISSUE_NUMBER")
	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ISSUE_DATE")
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "DESIGNED_BY")
	public String getDesignedBy() {
		return designedBy;
	}

	public void setDesignedBy(String designedBy) {
		this.designedBy = designedBy;
	}

	@Column(name = "PRINTED_BY")
	public String getPrintedBy() {
		return printedBy;
	}

	public void setPrintedBy(String printedBy) {
		this.printedBy = printedBy;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="stamp")
	@XmlElement(name="item")
	public List<StampItem> getItems() {
		return items;
	}

	public void setItems(List<StampItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "StampItem [issueNumber=" + issueNumber + ", name=" + name
				+ ", issueDate=" + issueDate + ", designedBy=" + designedBy
				+ ", printedBy=" + printedBy + "]";
	}
}
