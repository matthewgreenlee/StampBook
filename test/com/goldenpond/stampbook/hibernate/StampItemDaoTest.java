package com.goldenpond.stampbook.hibernate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.StampItem;

public class StampItemDaoTest {

	private StampItemDao dao = new StampItemDao();
	private StampItem item = new StampItem();

	@Before
	public void setUp() {

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
		item.setId(18);
		StampItem fetched = dao.fetch(item);
		assertEquals(18, fetched.getId());
		assertEquals("2012-04", fetched.getIssueNumber());
		assertEquals("a designer", fetched.getDesignedBy());
		assertEquals("a printer", fetched.getPrintedBy());
	}

	@Test
	public void testUpdate() {
		item.setId(18);
		item.setIssueNumber("2012-05");
		item.setName("a name");
		item.setDesignedBy("another designer");
		item.setPrintedBy("another printer");
		dao.update(item);
	}

	@Test
	public void testDelete() {
		item.setId(18);
		dao.delete(item);
	}

	@After
	public void tearDown() {
		
	}
}
