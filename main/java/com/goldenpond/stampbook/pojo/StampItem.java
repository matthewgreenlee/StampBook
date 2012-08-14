package com.goldenpond.stampbook.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STAMP_ITEM")
@XmlRootElement
public class StampItem {

	private Long id;

	private String serialNumber;

	private String name;

	private Long face;

	private Stamp stamp;

	public StampItem() {
		super();
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

	@Column(name = "FACE")
	public Long getFace() {
		return face;
	}

	public void setFace(Long face) {
		this.face = face;
	}

	@ManyToOne
	@JoinColumn(name = "STAMP_ID")
	public Stamp getStamp() {
		return stamp;
	}

	public void setStamp(Stamp stamp) {
		this.stamp = stamp;
	}
}
