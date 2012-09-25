package com.goldenpond.stampbook.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.hibernate.UserCollectionDaoImpl;
import com.goldenpond.stampbook.pojo.UserCollection;

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

	public void add(UserCollection userCollection) {
		userCollectionDaoImpl.create(userCollection);
	}

	public void remove(UserCollection userCollection) {
		userCollectionDaoImpl.delete(userCollection);
	}

	public void modify(UserCollection userCollection) {
		userCollectionDaoImpl.update(userCollection);
	}

	public List<UserCollection> getCollections(long userId) {
		return userCollectionDaoImpl.findAll(userId);
	}

	public UserCollection add(long userId, long stampItemId) {
		UserCollection uc = new UserCollection();
		uc.setUserId(userId);
		uc.setStampItemId(stampItemId);
		userCollectionDaoImpl.create(uc);
		return uc;
	}

	public UserCollection remove(long userId, long stampItemId) {
		UserCollection uc = get(userId, stampItemId);
		userCollectionDaoImpl.delete(uc);
		return uc;
	}

	public UserCollection get(long userId, long stampItemId) {
		return userCollectionDaoImpl.get(userId, stampItemId);
	}
}
