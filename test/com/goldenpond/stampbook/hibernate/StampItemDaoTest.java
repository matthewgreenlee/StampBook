package com.goldenpond.stampbook.hibernate;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.StampItem;

public class StampItemDaoTest extends TestCase {

	private StampItemDao dao;
	private StampItem item;
	private static final int stampItemId = 22;

	@Before
	public void setUp() {
		dao = new StampItemDao();
		item = new StampItem();
	}

	@Test
	public void testCreate() {
		item.setIssueNumber("2012-04");
		item.setName("a name");
		item.setDesignedBy("a designer");
		item.setPrintedBy("a printer");
		dao.create(item);
	}

	@Test
	public void testFetch() {
		item.setId(stampItemId);
		StampItem fetched = dao.fetch(item);
		assertEquals(stampItemId, fetched.getId());
		assertEquals("2012-04", fetched.getIssueNumber());
		assertEquals("a designer", fetched.getDesignedBy());
		assertEquals("a printer", fetched.getPrintedBy());
	}

	@Test
	public void testUpdate() {
		item.setId(stampItemId);
		item.setIssueNumber("stampItemId12-05");
		item.setName("a name");
		item.setDesignedBy("another designer");
		item.setPrintedBy("another printer");
		dao.update(item);
	}

	@Test
	public void testDelete() {
		item.setId(stampItemId);
		dao.delete(item);
	}

	@After
	public void tearDown() {

	}
}
