package com.goldenpond.stampbook.dao.hibernate;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.pojo.Stamp;

public class StampDaoTest extends TestCase {

	private StampDaoImpl dao = new StampDaoImpl();
	private Stamp item = new Stamp();
	private final long ID = 41;

	@Before
	protected void setUp() {

	}

	@Test
	public void testCreateWithNothingReturn() {
		item.setIssueNumber("2012-04");
		item.setName("a name");
		dao.create(item);
	}

	@Test
	public void testCreateWithIssueNumber() {

		String randomIssueNumber = "2012-" + new Random().nextInt(100);
		item = dao.create(randomIssueNumber);

		assertNotNull(item.getId());
		assertEquals(randomIssueNumber, item.getIssueNumber());
		assertNull(item.getName());
	}

	@Test
	public void testFetch() {
		Stamp fetched = dao.fetchById(ID);
		assertEquals(ID, fetched.getId());
		assertEquals("2012-04", fetched.getIssueNumber());
	}

	@Test
	public void testUpdate() {
		item.setId(ID);
		item.setIssueNumber("2012-05");
		item.setName("a name");
		dao.update(item);
	}

	@Test
	public void testDelete() {
		item.setId(ID);
		dao.delete(item);
	}

	@Test
	public void testFetchByIssueNumber() {
		Stamp item = dao.fetchByIssueNumber("2012-02");
		assertNotNull(item);
		assertNotNull(item.getId());
		assertEquals("2012-02", item.getIssueNumber());
	}

	@Test
	public void testFindAll() {
		List<Stamp> stamps = dao.findAll();
		assertEquals(10, stamps.size());
	}

	@After
	protected void tearDown() {

	}
}
