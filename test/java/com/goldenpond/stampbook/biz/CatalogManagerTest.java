package com.goldenpond.stampbook.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.goldenpond.stampbook.pojo.Stamp;

public class CatalogManagerTest {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"services.xml"});
	private CatalogManager manager;
	private Stamp stamp;

	private static final Long existingId = Long.valueOf(1);
	private static final Long nonExistentId = Long.valueOf(-1);
	private static final String existingIssueNumber = "2012-01";

	@Before
	public void setUp() {
		manager = ctx.getBean(CatalogManager.class);
		stamp = ctx.getBean(Stamp.class);
		stamp.setIssueNumber("2012-" + new Random().nextInt(100));
		stamp.setName("a name");
		stamp.setIssueDate(new Date());
		stamp.setDesignedBy("a designer");
		stamp.setPrintedBy("a printer");
	}

	@Test
	public void testAdd() {
		manager.add(stamp);
		assertNotNull(stamp.getId());
	}

	@Test(expected = HibernateException.class)
	public void testAddDuplicateIssueNumber() {
		stamp.setIssueNumber(existingIssueNumber);
		manager.add(stamp);
	}

	@Test
	public void testListAll() {
		List<Stamp> stamps = manager.listAll();
		assertNotNull(stamps);
		assertTrue(stamps.size() > 0);
	}

	@Test
	public void testGetExisting() {
		stamp = manager.get(existingId);
		assertNotNull(stamp);
	}

	@Test(expected = HibernateException.class)
	public void testGetNonExistant() {
		manager.get(nonExistentId);
	}

	@Test
	public void testModifyIssueDate() {
		stamp = manager.get(existingId);
		stamp.setIssueDate(new Date());
		manager.modify(stamp);
	}

	@Test(expected = HibernateException.class)
	public void testRemoveCreated() {
		manager.add(stamp);
		assertNotNull(stamp.getId());
		manager.remove(stamp);
		assertNotNull(stamp.getId());
		manager.get(stamp.getId());
	}

	@Test(expected = HibernateException.class)
	public void testRemoveNonExistent() {
		stamp.setId(nonExistentId);
		manager.remove(stamp);
	}

	@After
	public void tearDown() {
		manager = null;
		stamp = null;
	}
}
