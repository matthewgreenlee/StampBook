package com.goldenpond.stampbook.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.hibernate.UserCollectionDao;
import com.goldenpond.stampbook.pojo.UserCollection;

@Component
@Scope("singleton")
public class CollectionService {

	@Autowired(required=true) UserCollectionDao userCollectionDao;

	public UserCollectionDao getUserCollectionDao() {
		return userCollectionDao;
	}

	public void setUserCollectionDao(UserCollectionDao userCollectionDao) {
		this.userCollectionDao = userCollectionDao;
	}

	public void add(UserCollection userCollection) {
		userCollectionDao.create(userCollection);
	}

	public void remove(UserCollection userCollection) {
		userCollectionDao.delete(userCollection);
	}

	public void modify(UserCollection userCollection) {
		userCollectionDao.update(userCollection);
	}

	public List<UserCollection> getCollections(long userId) {
		return userCollectionDao.findAll(userId);
	}

	public UserCollection add(long userId, long stampItemId) {
		UserCollection uc = new UserCollection();
		uc.setUserId(userId);
		uc.setStampItemId(stampItemId);
		userCollectionDao.create(uc);
		return uc;
	}

	public UserCollection remove(long userId, long stampItemId) {
		UserCollection uc = get(userId, stampItemId);
		userCollectionDao.delete(uc);
		return uc;
	}

	public UserCollection get(long userId, long stampItemId) {
		return userCollectionDao.get(userId, stampItemId);
	}
}
