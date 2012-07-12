package com.goldenpond.stampbook.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.pojo.StampItem;

public class StampItemDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public StampItem create(String serialNumber, String name) {

		StampItem item = new StampItem(serialNumber, name);

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(item);
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
			throw he;
		}
		finally {
			session.close();
		}
		return item;
	}

}
