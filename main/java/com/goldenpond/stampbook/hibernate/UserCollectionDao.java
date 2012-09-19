package com.goldenpond.stampbook.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.pojo.UserCollection;

public class UserCollectionDao extends Dao {

	public List<UserCollection> findAll(long userId) {
		Session s = getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("from UserCollection");
		List uc = q.list();
		tx.commit();
		return uc;
	}

	public UserCollection get(long userId, long stampItemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
