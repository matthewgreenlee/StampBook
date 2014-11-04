package com.goldenpond.stampbook.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stamps {
	// named stamp instead of stamps to bind to <stamp> element for each stamp
	private List<StampVO> stampVO;

	public Stamps() {
	}

	public Stamps(List<StampVO> stampVO) {
		this.stampVO = stampVO;
	}

	public List<StampVO> getStamp() {
		return stampVO;
	}

	public void setStamp(List<StampVO> stampVO) {
		this.stampVO = stampVO;
	}

}
