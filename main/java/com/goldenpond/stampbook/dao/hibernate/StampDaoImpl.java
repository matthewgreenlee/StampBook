package com.goldenpond.stampbook.dao.hibernate;

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

import com.goldenpond.stampbook.dao.StampDao;
import com.goldenpond.stampbook.pojo.Item;
import com.goldenpond.stampbook.pojo.StampVO;

@Component
@Scope("singleton")
public class StampDaoImpl extends DaoImpl implements StampDao {

	@Override
	public StampVO create(String issueNumber) {

		StampVO stampVO = new StampVO();
		stampVO.setIssueNumber(issueNumber);
		stampVO.setItems(new ArrayList<Item>());

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(stampVO);
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
			throw he;
		}
		finally {
			session.close();
		}
		return stampVO;
	}

	@Override
	public StampVO fetchById(long id) {

		StampVO selected = new StampVO();
		
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

	@SuppressWarnings("unchecked")
	@Override
	public List<StampVO> findAll() {

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<StampVO> stampVOs = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from StampVO");	// here "Stamp" is case sensitive
			stampVOs = q.list();
			for (StampVO s : stampVOs) {
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
		return stampVOs;
	}

	@Override
	public StampVO fetchByIssueNumber(String issueNumber) {

		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		StampVO stampVO = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from Stamp " +
					"where ISSUE_NUMBER = :issueNumber"
					);
			q.setParameter("issueNumber", issueNumber);
			stampVO = (StampVO) q.uniqueResult();
			tx.commit();
		}
		catch (HibernateException he) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		return stampVO;
	}
}
