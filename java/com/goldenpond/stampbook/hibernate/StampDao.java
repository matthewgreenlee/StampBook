package com.goldenpond.stampbook.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.HibernateUtil;
import com.goldenpond.stampbook.Stamp;

public class StampDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void create(Stamp item) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(item);
		transaction.commit();
		session.close();
	}

	public Stamp create(String issueNumber) {
		Session session = sessionFactory.openSession();
		Stamp item = new Stamp();
		item.setIssueNumber(issueNumber);
		Transaction tx = session.beginTransaction();
		session.persist(item);
		tx.commit();
		session.close();
		return item;
	}

	public Stamp fetch(Stamp item) {
		Session session = sessionFactory.openSession();
		Stamp selected = new Stamp();
		Transaction transaction = session.beginTransaction();
		session.load(selected, item.getId());
		transaction.commit();
		session.close();
		return selected;
	}

	public void update(Stamp item) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(item);
		transaction.commit();
		session.close();
	}

	public void delete(Stamp item) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(item);
		transaction.commit();
		session.close();
	}
}
