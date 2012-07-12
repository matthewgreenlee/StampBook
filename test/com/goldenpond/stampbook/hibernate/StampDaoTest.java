package com.goldenpond.stampbook.hibernate;

import java.util.Date;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.pojo.Stamp;

public class StampDaoTest extends TestCase {

	private StampDao dao = new StampDao();
	private Stamp item = new Stamp();
	private long id = 37;

	@Before
	protected void setUp() {

	}

	@Test
	public void testCreateWithNothingReturn() {
		item.setIssueNumber("2012-04");
		item.setName("a name");
		item.setIssueDate(new Date());
		item.setDesignedBy("a designer");
		item.setPrintedBy("a printer");
		dao.create(item);
	}

	@Test
	public void testCreateByNameAndNumber() {

		String randomIssueNumber = "2012-" + new Random().nextInt(100);
		item = dao.create(randomIssueNumber, "a name");
		this.id = item.getId();

		assertNotNull(item.getId());
		assertEquals(randomIssueNumber, item.getIssueNumber());
		assertEquals("a name", item.getName());
		assertNull(item.getIssueDate());
		assertNull(item.getDesignedBy());
		assertNull(item.getPrintedBy());
	}

	@Test
	public void testFetch() {
		item.setId(id);
		Stamp fetched = dao.fetch(item);
		assertEquals(id, fetched.getId());
		assertEquals("2012-04", fetched.getIssueNumber());
		assertEquals("a designer", fetched.getDesignedBy());
		assertEquals("a printer", fetched.getPrintedBy());
	}

	@Test
	public void testUpdate() {
		item.setId(id);
		item.setIssueNumber("stampItemId12-05");
		item.setName("a name");
		item.setDesignedBy("another designer");
		item.setPrintedBy("another printer");
		dao.update(item);
	}

	@Test
	public void testDelete() {
		item.setId(id);
		dao.delete(item);
	}

	@After
	protected void tearDown() {

	}
}
