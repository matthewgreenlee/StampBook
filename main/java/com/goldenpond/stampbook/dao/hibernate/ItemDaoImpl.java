package com.goldenpond.stampbook.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.ItemDao;
import com.goldenpond.stampbook.pojo.Item;
import com.goldenpond.stampbook.pojo.StampVO;

@Component
@Scope("singleton")
public class ItemDaoImpl extends DaoImpl implements ItemDao {

	@Override
	public Item addToExistingStamp(StampVO stampVO, Item item) {

		item.setStamp(stampVO);
		stampVO.getItems().add(item);

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(stampVO);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> fetchItems(StampVO stampVO) {

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Item> items = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Item " +
					"where STAMP_ID = :stampId"
					);
			q.setParameter("stampId", stampVO.getId());
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
	public void removeFromExistingStamp(StampVO stampVO, Item item) {

		stampVO.getItems().remove(item);

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(stampVO);
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
			StampVO stampVO = (StampVO) q.uniqueResult();
			q = session.createQuery("from Item where SERIAL_NUMBER = :serialNumber and STAMP_ID = :stampId");
			q.setParameter("serialNumber", serialNumber);
			q.setParameter("stampId", stampVO.getId());
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
