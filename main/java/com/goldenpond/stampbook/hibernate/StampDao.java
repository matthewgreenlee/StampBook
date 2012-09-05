package com.goldenpond.stampbook.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.StampItem;

public class StampDao extends Dao {

	public Stamp create(String issueNumber) {

		Stamp stamp = new Stamp();
		stamp.setIssueNumber(issueNumber);
//		stamp.setItems(new ArrayList<StampItem>());

		Session session = getSessionFactory().openSession();
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
		
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			selected = (Stamp) session.load(Stamp.class, stamp.getId());
			session.load(selected, stamp.getId());
			// actively load associated items
			for (StampItem item : selected.getItems()) {
				;
			}
			tx.commit();
		}
		catch (ObjectNotFoundException e) {
			selected = null;
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

	public List<Stamp> findAll() {

		Session session = getSessionFactory().openSession();
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

	public Stamp fetchByIssueNumber(String issueNumber) {

		Session session = getSessionFactory().openSession();
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
