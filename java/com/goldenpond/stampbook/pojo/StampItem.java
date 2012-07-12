package com.goldenpond.stampbook.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STAMP_ITEM")
public class StampItem {

	private Long id;

	private String serialNumber;

	private String name;

	private String money;

	public StampItem(String serialNumber, String name) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
	}

	@Id
	@Column(name = "STAMP_ITEM_ID")
	@GeneratedValue(generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SERIAL_NUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MONEY")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
}
