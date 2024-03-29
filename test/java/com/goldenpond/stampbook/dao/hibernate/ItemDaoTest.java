package com.goldenpond.stampbook.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.pojo.StampVO;
import com.goldenpond.stampbook.pojo.Item;

public class ItemDaoTest extends TestCase {

	private ItemDaoImpl dao = new ItemDaoImpl();

	@Before
	protected void setUp() {
		
	}

	@Test
	public void testAddToExistingStamp() {
		StampVO existingStamp = new StampVO();
		existingStamp.setId(8);
		existingStamp.setIssueNumber("2012-02");
		existingStamp.setItems(new ArrayList<Item>());
		Item newItem = new Item();
		newItem.setSerialNumber("1-1");
		Item item = dao.addToExistingStamp(existingStamp, newItem);
		assertNotNull(item.getId());
	}

	@Test
	public void testFetchAll() {
		StampVO stampVO = new StampVO();
		stampVO.setId(8);
		List<Item> items = dao.fetchItems(stampVO);
		assertNotNull(items);
		assertEquals(5, items.size());
	}

	@Test
	public void testDelete() {
		Item item = new Item();
		item.setId(Long.valueOf(7));
		dao.delete(item);
	}

	@After
	protected void tearDown() {
		
	}
}
