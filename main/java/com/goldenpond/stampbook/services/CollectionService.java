package com.goldenpond.stampbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.hibernate.CollectionDaoImpl;
import com.goldenpond.stampbook.pojo.Collection;

@Component
@Scope("singleton")
public class CollectionService {

	@Autowired(required=true) CollectionDaoImpl collectionDaoImpl;

	public List<Collection> getAll(long userId) {
		return collectionDaoImpl.findAll(userId);
	}

	public void add(Collection collection) {
		collectionDaoImpl.create(collection);
	}

	public Collection remove(long userId, long itemId) {
		Collection c = get(userId, itemId);
		collectionDaoImpl.delete(c);
		return c;
	}

	public Collection get(long userId, long itemId) {
		return collectionDaoImpl.get(userId, itemId);
	}
}
