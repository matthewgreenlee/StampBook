package com.goldenpond.stampbook.hibernate;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.goldenpond.stampbook.pojo.StampItem;

public class StampItemDaoTest extends TestCase {

	private StampItemDao dao = new StampItemDao();

	@Before
	protected void setUp() {
		
	}

	@Test
	public void testCreateWithoutFace() {
		StampItem one;
		one = dao.create("1-1", "an item name");
		assertNotNull(one.getId());
	}

	@After
	protected void tearDown() {
		
	}
}
