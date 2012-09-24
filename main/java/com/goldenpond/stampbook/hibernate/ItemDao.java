package com.goldenpond.stampbook.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.ItemDaoI;
import com.goldenpond.stampbook.pojo.Item;
import com.goldenpond.stampbook.pojo.Stamp;

@Component
@Scope("singleton")
public class ItemDao extends Dao implements ItemDaoI {

	@Override
	public Item addToExistingStamp(Stamp stamp, Item item) {

		item.setStamp(stamp);
		stamp.getItems().add(item);

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

	@Override
	public List<Item> fetchItems(Stamp stamp) {

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Item> items = null;
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

	@Override
	public void removeFromExistingStamp(Stamp stamp, Item item) {

		stamp.getItems().remove(item);

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

	@Override
	public Item fetch(String issueNumber, String serialNumber) {

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Item item = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Stamp where ISSUE_NUMBER = :issueNumber");
			q.setParameter("issueNumber", issueNumber);
			Stamp stamp = (Stamp) q.uniqueResult();
			q = session.createQuery("from StampItem where SERIAL_NUMBER = :serialNumber and STAMP_ID = :stampId");
			q.setParameter("serialNumber", serialNumber);
			q.setParameter("stampId", stamp.getId());
			item = (Item) q.uniqueResult();
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
