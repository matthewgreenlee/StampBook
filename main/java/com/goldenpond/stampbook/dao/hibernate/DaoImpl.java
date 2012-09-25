package com.goldenpond.stampbook.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.dao.Dao;


public abstract class DaoImpl implements Dao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void update(Object obj) {
	
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(obj);
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

	@Override
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
