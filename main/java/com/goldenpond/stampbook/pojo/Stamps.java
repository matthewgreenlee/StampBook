package com.goldenpond.stampbook.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stamps {
	// named stamp instead of stamps to bind to <stamp> element for each stamp
	private List<Stamp> stamp;

	public Stamps() {
	}

	public Stamps(List<Stamp> stamp) {
		this.stamp = stamp;
	}

	public List<Stamp> getStamp() {
		return stamp;
	}

	public void setStamp(List<Stamp> stamp) {
		this.stamp = stamp;
	}

}
