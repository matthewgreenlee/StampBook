package com.goldenpond.stampbook.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public abstract class Dao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void update(Object obj) {
	
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(obj);
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

	public void delete(Object obj) {
	
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(obj);
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

	public void create(Object obj) {
	
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(obj);
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
}
