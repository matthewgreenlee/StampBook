package com.goldenpond.stampbook.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.pojo.Collection;

@Component
@Scope("singleton")
public class UserCollectionDaoImpl extends DaoImpl {

	public List<Collection> findAll(long userId) {
		Session s = getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("from UserCollection");
		List uc = q.list();
		tx.commit();
		return uc;
	}

	public Collection get(long userId, long stampItemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
