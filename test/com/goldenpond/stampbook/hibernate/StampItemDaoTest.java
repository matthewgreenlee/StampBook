package com.goldenpond.stampbook.hibernate;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.StampItem;

public class StampItemDaoTest extends TestCase {

	private StampItemDao dao = new StampItemDao();
	private StampItem item = new StampItem();
	private static final int ID = 32;

	@Before
	protected void setUp() {

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
		item.setId(ID);
		StampItem fetched = dao.fetch(item);
		assertEquals(ID, fetched.getId());
		assertEquals("2012-04", fetched.getIssueNumber());
		assertEquals("a designer", fetched.getDesignedBy());
		assertEquals("a printer", fetched.getPrintedBy());
	}

	@Test
	public void testUpdate() {
		item.setId(ID);
		item.setIssueNumber("stampItemId12-05");
		item.setName("a name");
		item.setDesignedBy("another designer");
		item.setPrintedBy("another printer");
		dao.update(item);
	}

	@Test
	public void testDelete() {
		item.setId(ID);
		dao.delete(item);
	}

	@After
	protected void tearDown() {

	}
}
