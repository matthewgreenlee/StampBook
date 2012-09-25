package com.goldenpond.stampbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.hibernate.UserCollectionDaoImpl;
import com.goldenpond.stampbook.pojo.Collection;

@Component
@Scope("singleton")
public class CollectionService {

	@Autowired(required=true) UserCollectionDaoImpl userCollectionDaoImpl;

	public UserCollectionDaoImpl getUserCollectionDao() {
		return userCollectionDaoImpl;
	}

	public void setUserCollectionDao(UserCollectionDaoImpl userCollectionDaoImpl) {
		this.userCollectionDaoImpl = userCollectionDaoImpl;
	}

	public void add(Collection collection) {
		userCollectionDaoImpl.create(collection);
	}

	public void remove(Collection collection) {
		userCollectionDaoImpl.delete(collection);
	}

	public void modify(Collection collection) {
		userCollectionDaoImpl.update(collection);
	}

	public List<Collection> getCollections(long userId) {
		return userCollectionDaoImpl.findAll(userId);
	}

	public Collection add(long userId, long stampItemId) {
		Collection uc = new Collection();
		uc.setUserId(userId);
		uc.setStampItemId(stampItemId);
		userCollectionDaoImpl.create(uc);
		return uc;
	}

	public Collection remove(long userId, long stampItemId) {
		Collection uc = get(userId, stampItemId);
		userCollectionDaoImpl.delete(uc);
		return uc;
	}

	public Collection get(long userId, long stampItemId) {
		return userCollectionDaoImpl.get(userId, stampItemId);
	}
}
