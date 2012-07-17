package com.goldenpond.stampbook.biz;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.pojo.Stamp;

public class CatalogManagerTest {

	private CatalogManager manager;

	private static final Long existingId = Long.valueOf(1);
	private static final Long nonExistentId = Long.valueOf(-1);

	@Before
	public void setUp() {
		manager = CatalogManager.getInstance();
	}

	@Test
	public void testAdd() {
		Stamp stamp = createDummyStampInMemory();
		manager.add(stamp);
		assertNotNull(stamp.getId());
	}

	private Stamp createDummyStampInMemory() {
		Stamp stamp = new Stamp();
		stamp.setIssueNumber(getIssueNumber());
		stamp.setName("a name");
		stamp.setIssueDate(new Date());
		stamp.setDesignedBy("a designer");
		stamp.setPrintedBy("a printer");
		return stamp;
	}

	private String getIssueNumber() {
		return "2012-" + new Random().nextInt(100);
	}

	@Test(expected = HibernateException.class)
	public void testAddDuplicateIssueNumber() {
		String duplicateIssueNumber = getIssueNumber();
		Stamp stamp = createDummyStampInMemory();
		stamp.setIssueNumber(duplicateIssueNumber);
		manager.add(stamp);

		Stamp another = createDummyStampInMemory();
		another.setIssueNumber(duplicateIssueNumber);
		manager.add(another);
	}

	@Test
	public void testListAll() {
		List<Stamp> stamps = manager.listAll();
		assertNotNull(stamps);
	}

	@Test
	public void testGetExisting() {
		Stamp stamp = manager.get(existingId);
		assertNotNull(stamp);
	}

	@Test(expected = HibernateException.class)
	public void testGetNonExistant() {
		manager.get(nonExistentId);
	}

	@Test
	public void testModify() {
		Stamp stamp = manager.get(existingId);
		stamp.setIssueDate(new Date());
		manager.modify(stamp);
	}

	@Test
	public void testRemoveCreated() {
		Stamp stamp = createDummyStampInMemory();
		manager.add(stamp);
		manager.remove(stamp);
	}

	@Test(expected = HibernateException.class)
	public void testRemoveNonExistent() {
		Stamp stamp = createDummyStampInMemory();
		stamp.setId(nonExistentId);
		manager.remove(stamp);
	}

	@After
	public void tearDown() {
		manager = null;
	}
}
