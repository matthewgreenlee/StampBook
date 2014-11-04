package com.goldenpond.stampbook.dao;

import java.util.List;

import com.goldenpond.stampbook.pojo.StampVO;

public interface StampDao {

	public abstract StampVO create(String issueNumber);

	public abstract StampVO fetchById(long id);

	public abstract List<StampVO> findAll();

	public abstract StampVO fetchByIssueNumber(String issueNumber);

}