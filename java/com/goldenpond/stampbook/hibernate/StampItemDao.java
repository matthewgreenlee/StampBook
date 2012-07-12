package com.goldenpond.stampbook.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.pojo.Stamp;
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

	public StampItem fetch(String issueNumber, String serialNumber) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		StampItem item = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Stamp where ISSUE_NUMBER = :issueNumber");
			q.setParameter("issueNumber", issueNumber);
			Stamp stamp = (Stamp) q.uniqueResult();
			q = session.createQuery("from StampItem where SERIAL_NUMBER = :serialNumber and STAMP_ID = :stampId");
			q.setParameter("serialNumber", serialNumber);
			q.setParameter("stampId", stamp.getId());
			item = (StampItem) q.uniqueResult();
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
