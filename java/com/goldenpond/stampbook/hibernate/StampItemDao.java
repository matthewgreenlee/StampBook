package com.goldenpond.stampbook.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.HibernateUtil;
import com.goldenpond.stampbook.StampItem;

public class StampItemDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void create(StampItem item) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(item);
		transaction.commit();
		session.close();
	}

	public StampItem fetch(StampItem item) {
		Session session = sessionFactory.openSession();
		StampItem selected = new StampItem();
		Transaction transaction = session.beginTransaction();
		session.load(selected, item.getId());
		transaction.commit();
		session.close();
		return selected;
	}

	public void update(StampItem item) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(item);
		transaction.commit();
		session.close();
	}

	public void delete(StampItem item) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(item);
		transaction.commit();
		session.close();
	}
}
