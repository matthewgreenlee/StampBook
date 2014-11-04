package com.goldenpond.stampbook.pojo;

import java.util.ArrayList;
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
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STAMP")
@XmlRootElement
@XmlType(propOrder={"id", "name", "type", "issueNumber", "items"})
public class StampVO {

	private long id;

	private String issueNumber;

	private String name;

	private String type;

	private List<Item> items;

	public StampVO() {
		super();
		items = new ArrayList<Item>();
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

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="stamp")
	@XmlElement(name="item")
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item getItem(String serialNumber) {
		for (Item i : items) {
			if (serialNumber.equals(i.getSerialNumber())) {
				return i;
			}
		}
		return null;
	}

	public boolean hasItem(Item item) {
		for (Item i : items) {
			if (i.getSerialNumber().equals(item.getSerialNumber())) {
				return true;
			}
		}
		return false;
	}

	public void addItem(Item item) {
		items.add(item);
		item.setStamp(this);
	}
}
