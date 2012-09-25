package com.goldenpond.stampbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.CollectionDao;
import com.goldenpond.stampbook.dao.hibernate.CollectionDaoImpl;
import com.goldenpond.stampbook.pojo.Collection;

@Component
@Scope("singleton")
public class CollectionService {

	@Autowired(required=true) CollectionDaoImpl collectionDaoImpl;

	public CollectionDao getUserCollectionDao() {
		return collectionDaoImpl;
	}

	public void setUserCollectionDao(CollectionDaoImpl collectionDaoImpl) {
		this.collectionDaoImpl = collectionDaoImpl;
	}

	public void add(Collection collection) {
		collectionDaoImpl.create(collection);
	}

	public void remove(Collection collection) {
		collectionDaoImpl.delete(collection);
	}

	public void modify(Collection collection) {
		collectionDaoImpl.update(collection);
	}

	public List<Collection> getCollections(long userId) {
		return collectionDaoImpl.findAll(userId);
	}

	public Collection add(long userId, long stampItemId) {
		Collection uc = new Collection();
		uc.setUserId(userId);
		uc.setStampItemId(stampItemId);
		collectionDaoImpl.create(uc);
		return uc;
	}

	public Collection remove(long userId, long stampItemId) {
		Collection uc = get(userId, stampItemId);
		collectionDaoImpl.delete(uc);
		return uc;
	}

	public Collection get(long userId, long stampItemId) {
		return collectionDaoImpl.get(userId, stampItemId);
	}
}
