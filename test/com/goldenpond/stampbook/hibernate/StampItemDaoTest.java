package com.goldenpond.stampbook.hibernate;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.StampItem;

public class StampItemDaoTest extends TestCase {

	private StampItemDao dao = new StampItemDao();

	@Before
	protected void setUp() {
		
	}

	@Test
	public void testCreate() {
		StampItem item;
		item = dao.create("1-1");
		assertNotNull(item.getId());
	}

	@Test
	public void testCreateForExistingStamp() {
		Stamp existingStamp = new Stamp();
		existingStamp.setId(8);
		existingStamp.setIssueNumber("2012-02");
		existingStamp.setItems(new ArrayList<StampItem>());

		StampItem item = dao.create(existingStamp, "1-1");
		assertNotNull(item.getId());
	}

	@Test
	public void testDelete() {
		StampItem item = new StampItem();
		item.setId(Long.valueOf(4));
		dao.delete(item);
	}

	@After
	protected void tearDown() {
		
	}
}
