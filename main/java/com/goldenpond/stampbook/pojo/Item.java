package com.goldenpond.stampbook.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ITEM")
@XmlRootElement(name = "item")
@XmlType(propOrder = { "serialNumber", "name", "face", "image" })
public class Item {

	private Long id;

	private String serialNumber;

	private String name;

	private BigDecimal face;

	private String image;

	private StampVO stampVO;

	public Item() {
		super();
	}

	@Id
	@Column(name = "ITEM_ID")
	@GeneratedValue(generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@XmlTransient
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
	public BigDecimal getFace() {
		return face;
	}

	public void setFace(BigDecimal face) {
		this.face = face;
	}

	@Column(name = "IMAGE")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToOne
	@JoinColumn(name = "STAMP_ID", nullable=false)
	@XmlTransient
	public StampVO getStamp() {
		return stampVO;
	}

	public void setStamp(StampVO stampVO) {
		this.stampVO = stampVO;
	}
}
