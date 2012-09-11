package com.goldenpond.stampbook.hibernate;

import java.util.ArrayList;
import java.util.List;

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
	public void testAddToExistingStamp() {
		Stamp existingStamp = new Stamp();
		existingStamp.setId(8);
		existingStamp.setIssueNumber("2012-02");
		existingStamp.setItems(new ArrayList<StampItem>());
		StampItem newItem = new StampItem();
		newItem.setSerialNumber("1-1");
		StampItem item = dao.addToExistingStamp(existingStamp, newItem);
		assertNotNull(item.getId());
	}

	@Test
	public void testFetchAll() {
		Stamp stamp = new Stamp();
		stamp.setId(8);
		List<StampItem> items = dao.fetchItems(stamp);
		assertNotNull(items);
		assertEquals(5, items.size());
	}

	@Test
	public void testDelete() {
		StampItem item = new StampItem();
		item.setId(Long.valueOf(7));
		dao.delete(item);
	}

	@After
	protected void tearDown() {
		
	}
}
