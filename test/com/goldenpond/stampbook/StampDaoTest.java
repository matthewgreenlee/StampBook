package com.goldenpond.stampbook;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StampDaoTest extends TestCase {

	private StampDao dao = new StampDao();;
	private Stamp item = new Stamp();

	@Before
	public void setUp() {
		dao.connect();
	}

	@Test
	public void testCreate() {
		item.setIssueNumber("2012-03");
		item.setName("a stamp");
//		item.setIssueDate(new Date());
		item.setDesignedBy("a designer");
		item.setPrintedBy("a printer");
		dao.create(item);
	}

	@Test
	public void testFetch() {
		Stamp result = dao.fetch("2012-03");
		assertEquals("2012-03", result.getIssueNumber());
		assertEquals("a stamp", result.getName());
		assertEquals("a designer", result.getDesignedBy());
		assertEquals("a printer", result.getPrintedBy());
	}

	@Test
	public void testUpdate() {
		item.setIssueNumber("2012-03");
		item.setDesignedBy("another designer");
		item.setPrintedBy("another printer");
		dao.update(item);
	}

	@Test
	public void testDelete() {
		item.setIssueNumber("2012-03");
		dao.delete(item);
	}

	@After
	public void tearDown() {
		item = null;
		dao.disconnect();
	}
}
