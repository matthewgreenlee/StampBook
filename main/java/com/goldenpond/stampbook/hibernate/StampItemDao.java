package com.goldenpond.stampbook.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.StampItem;

public class StampItemDao extends Dao {

	public StampItem create(String serialNumber) {

		StampItem item = new StampItem();
		item.setSerialNumber(serialNumber);

		Session session = getSessionFactory().openSession();
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

	public StampItem addToExistingStamp(Stamp stamp, String serialNumber) {

		StampItem item = new StampItem();
		item.setSerialNumber(serialNumber);
//		item.setStamp(stamp);
//		stamp.getItems().add(item);

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(stamp);
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

	public List<StampItem> fetchAll(Stamp stamp) {

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<StampItem> items = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from StampItem " +
					"where STAMP_ID = :stampId"
					);
			q.setParameter("stampId", stamp.getId());
			items = q.list();
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		return items;
	}

	public void removeFromExistingStamp(Stamp stamp, StampItem item) {

//		stamp.getItems().remove(item);

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(stamp);
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
			throw he;
		}
		finally {
			session.close();
		}
	}

	public StampItem fetch(String issueNumber, String serialNumber) {

		Session session = getSessionFactory().openSession();
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
