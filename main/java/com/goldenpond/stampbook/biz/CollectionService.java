package com.goldenpond.stampbook.biz;

import java.util.List;

import com.goldenpond.stampbook.hibernate.UserCollectionDao;
import com.goldenpond.stampbook.pojo.User;
import com.goldenpond.stampbook.pojo.UserCollection;

public class CollectionService {

	private static CollectionService instance = new CollectionService();

	private UserCollectionDao dao;

	private CollectionService() {
		dao = new UserCollectionDao();
	}

	public static CollectionService getInstance() {
		return instance;
	}

	public void setDao(UserCollectionDao dao) {
		this.dao = dao;
	}

	public UserCollectionDao getUserCollectionDao() {
		return dao;
	}

	public void setUserCollectionDao(UserCollectionDao userCollectionDao) {
		this.dao = userCollectionDao;
	}

	public void add(UserCollection userCollection) {
		dao.create(userCollection);
	}

	public void remove(UserCollection userCollection) {
		dao.delete(userCollection);
	}

	public void modify(UserCollection userCollection) {
		dao.update(userCollection);
	}

	public List<UserCollection> getCollections(User user) {
		// list all collections with the same user id
		return null;
	}

	public List<UserCollection> getCollections(long userId) {
		return dao.findAll(userId);
	}

	public UserCollection add(long userId, long stampItemId) {
		UserCollection uc = new UserCollection();
		uc.setUserId(userId);
		uc.setStampItemId(stampItemId);
		dao.create(uc);
		return uc;
	}

	public UserCollection remove(long userId, long stampItemId) {
		UserCollection uc = get(userId, stampItemId);
		dao.delete(uc);
		return uc;
	}

	public UserCollection get(long userId, long stampItemId) {
		return dao.get(userId, stampItemId);
	}
}
