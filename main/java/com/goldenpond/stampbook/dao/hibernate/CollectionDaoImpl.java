package com.goldenpond.stampbook.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.CollectionDao;
import com.goldenpond.stampbook.pojo.Collection;

@Component
@Scope("singleton")
public class CollectionDaoImpl extends DaoImpl implements CollectionDao {

	@Override
	public List<Collection> findAll(long userId) {
		Session s = getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("from Collection");
		List uc = q.list();
		tx.commit();
		return uc;
	}

	@Override
	public Collection get(long userId, long itemId) {
		Session s = getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("from Collection where USER_ID = :userId and ITEM_ID = :itemId");
		q.setParameter("userId", userId);
		q.setParameter("itemId", itemId);
		Collection c = (Collection) q.uniqueResult();
		return c;
	}

}
