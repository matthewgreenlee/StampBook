package com.goldenpond.stampbook.domain;

import com.goldenpond.stampbook.dao.hibernate.StampDaoImpl;
import com.goldenpond.stampbook.exception.StampBookException;
import com.goldenpond.stampbook.pojo.StampVO;

public class Stamp {

	private StampVO vo;
	private StampDaoImpl dao = getBean(); // should be annotated

	public Stamp(StampVO vo) {
		this.vo = vo;
	}

	public void save() {
		if (dao.fetchByIssueNumber(vo.getIssueNumber()) != null) {
			throw new StampBookException("IssueNumber already exists");
		}
		try {
			dao.create(vo);
		} catch (Exception e) {
			throw new StampBookException(e);
		}
	}
}
