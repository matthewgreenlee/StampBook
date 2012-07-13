package com.goldenpond.stampbook.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.StampItem;

public class StampDao extends Dao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void create(Stamp stamp) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(stamp);
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

	public Stamp create(String issueNumber) {

		Stamp stamp = new Stamp();
		stamp.setIssueNumber(issueNumber);
		stamp.setItems(new ArrayList<StampItem>());

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(stamp);
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
			throw he;
		}
		finally {
			session.close();
		}
		return stamp;
	}

	public Stamp fetch(Stamp stamp) {

		Stamp selected = new Stamp();
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			selected = (Stamp) session.load(Stamp.class, stamp.getId());
			session.load(selected, stamp.getId());
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
			throw he;
		}
		finally {
			session.close();
		}
		return selected;
	}

	public void update(Stamp stamp) {

		Session session = sessionFactory.openSession();
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

	public List<Stamp> findAll() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Stamp> stamps = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Stamp");	// here "Stamp" is case sensitive
			stamps = q.list();
		}
		catch (HibernateException he) {
			tx.rollback();
			throw he;
		}
		finally {
			session.close();
		}
		return stamps;
	}

	public Stamp findByIssueNumber(String issueNumber) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Stamp stamp = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Stamp " +
					"where ISSUE_NUMBER = :issueNumber"
					);
			q.setParameter("issueNumber", issueNumber);
			stamp = (Stamp) q.uniqueResult();
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		return stamp;
	}
}
