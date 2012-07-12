package com.goldenpond.stampbook.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.pojo.StampItem;

public class StampItemDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public StampItem create(String stampId, String itemId, String name) {
		StampItem item = new StampItem();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(item);
		tx.commit();
		session.close();
		return item;
	}

}
