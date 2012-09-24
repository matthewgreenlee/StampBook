package com.goldenpond.stampbook.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.StampDaoI;
import com.goldenpond.stampbook.pojo.Item;
import com.goldenpond.stampbook.pojo.Stamp;

@Component
@Scope("singleton")
public class StampDao extends Dao implements StampDaoI {

	@Override
	public Stamp create(String issueNumber) {

		Stamp stamp = new Stamp();
		stamp.setIssueNumber(issueNumber);
		stamp.setItems(new ArrayList<Item>());

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

	@Override
	public Stamp fetchById(long id) {

		Stamp selected = new Stamp();
		
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.load(selected, id);
			Hibernate.initialize(selected.getItems());
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

	@Override
	public List<Stamp> findAll() {

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Stamp> stamps = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Stamp");	// here "Stamp" is case sensitive
			stamps = q.list();
			for (Stamp s : stamps) {
				Hibernate.initialize(s.getItems());
			}
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

	@Override
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
