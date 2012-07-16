package com.goldenpond.stampbook.biz;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.pojo.Stamp;

public class CatalogManagerTest extends TestCase {

	private CatalogManager manager;

	private static Long existingId = Long.valueOf(1);
	private static Long nonExistentId = Long.valueOf(-1);

	@Before
	protected void setUp() {
		manager = CatalogManager.getInstance();
	}

	@Test
	public void testAdd() {
		Stamp stamp = new Stamp();
		stamp.setIssueNumber("2012-23");
		stamp.setName("a name");
		stamp.setIssueDate(new Date());
		stamp.setDesignedBy("a designer");
		stamp.setPrintedBy("a printer");
		manager.add(stamp);
		assertNotNull(stamp.getId());
		nonExistentId = stamp.getId();
	}

	@Test
	public void testListAll() {
		List<Stamp> stamps = manager.listAll();
		assertNotNull(stamps);
	}

	@Test
	public void testGet() {
		Stamp stamp = manager.get(existingId);
		assertNotNull(stamp);
	}

	@Test
	public void testModify() {
		Stamp stamp = manager.get(existingId);
		stamp.setIssueDate(new Date());
		manager.modify(stamp);
	}

	@Test
	public void testRemove() {
		Stamp stamp = manager.get(nonExistentId);
		manager.remove(stamp);
	}

	@After
	protected void tearDown() {
		manager = null;
	}
}
