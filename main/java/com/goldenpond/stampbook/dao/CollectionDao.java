package com.goldenpond.stampbook.dao;

import java.util.List;

import com.goldenpond.stampbook.pojo.Collection;

public interface CollectionDao {

	public abstract List<Collection> findAll(long userId);

	public abstract Collection get(long userId, long stampItemId);

}