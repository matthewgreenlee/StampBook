package com.goldenpond.stampbook;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StampItemDaoTest {

	private StampItemDao dao;
	private StampItem item;

	@Before
	public void setUp() {
		dao = new StampItemDao();
		dao.connect();
		item = new StampItem();
	}

	@Test
	public void testCreate() {
		item.setIssueNumber("3");
		item.setName("name");
		dao.create(item);
	}
	@Test
	public void testFetch() {
		StampItem result = dao.fetch("3");
		assertEquals("3", result.getIssueNumber());
	}

	@Test
	public void testUpdate() {
		item.setIssueNumber("2");
		item.setName("what ever");
		dao.update(item);
	}

	@Test
	public void testDelete() {
		item.setIssueNumber("3");
		dao.delete(item);
	}

	@After
	public void tearDown() {
		item = null;
		dao.disconnect();
	}
}
