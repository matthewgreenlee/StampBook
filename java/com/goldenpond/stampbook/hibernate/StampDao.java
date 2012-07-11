package com.goldenpond.stampbook.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.HibernateUtil;
import com.goldenpond.stampbook.Stamp;

public class StampDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void create(Stamp stamp) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(stamp);
		transaction.commit();
		session.close();
	}

	public Stamp create(String issueNumber, String name) {

		Stamp stamp = new Stamp();
		stamp.setIssueNumber(issueNumber);
		stamp.setName(name);

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(stamp);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
		} finally {
			session.close();
		}
		return stamp;
	}

	public Stamp fetch(Stamp stamp) {
		Session session = sessionFactory.openSession();
		Stamp selected = new Stamp();
		Transaction transaction = session.beginTransaction();
		session.load(selected, stamp.getId());
		transaction.commit();
		session.close();
		return selected;
	}

	public void update(Stamp stamp) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(stamp);
		transaction.commit();
		session.close();
	}

	public void delete(Stamp stamp) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(stamp);
		transaction.commit();
		session.close();
	}
}
