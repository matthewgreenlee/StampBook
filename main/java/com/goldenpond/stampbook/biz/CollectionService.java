package com.goldenpond.stampbook.biz;

import java.util.List;

import com.goldenpond.stampbook.hibernate.UserCollectionDao;
import com.goldenpond.stampbook.pojo.User;
import com.goldenpond.stampbook.pojo.UserCollection;

public class CollectionService {

	private UserCollectionDao userCollectionDao;

	public void setDao(UserCollectionDao dao) {
		this.userCollectionDao = dao;
	}

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

	public List<UserCollection> getCollections(User user) {
		// list all collections with the same user id
		return null;
	}
}
