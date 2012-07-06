package com.goldenpond.stampbook.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.HibernateUtil;
import com.goldenpond.stampbook.StampItem;

public class StampItemDao {

	private Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	public void create(StampItem item) {
		Transaction transaction = session.beginTransaction();
		session.save(item);
		transaction.commit();
	}

	public StampItem fetch(StampItem item) {
		StampItem selected = new StampItem();
		Transaction transaction = session.beginTransaction();
		session.load(selected, item.getId());
		transaction.commit();
		return selected;
	}

	public void update(StampItem item) {
		Transaction transaction = session.beginTransaction();
		session.update(item);
		transaction.commit();
	}

	public void delete(StampItem item) {
		Transaction transaction = session.beginTransaction();
		session.delete(item);
		transaction.commit();
	}
}
