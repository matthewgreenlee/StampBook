package com.goldenpond.stampbook.dao;

import java.util.List;

import com.goldenpond.stampbook.pojo.Stamp;

public interface StampDao {

	public abstract Stamp create(String issueNumber);

	public abstract Stamp fetchById(long id);

	public abstract List<Stamp> findAll();

	public abstract Stamp fetchByIssueNumber(String issueNumber);

}