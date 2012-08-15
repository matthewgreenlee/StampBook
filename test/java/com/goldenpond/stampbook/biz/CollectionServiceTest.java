package com.goldenpond.stampbook.biz;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.goldenpond.stampbook.pojo.UserCollection;

public class CollectionServiceTest {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"services.xml", "daos.xml"});
	private CollectionService service;

	@Before
	public void setUp() {
		service = ctx.getBean(CollectionService.class);
	}

	@Test
	public void testAdd() {
		UserCollection userCollection = new UserCollection();
		userCollection.setUserId(Long.valueOf(1));
		userCollection.setStampItemId(Long.valueOf(1));
		service.add(userCollection);
		assertNotNull(userCollection.getId());
	}

	@Test
	public void testRemove() {
		
	}

	@After
	public void tearDown() {
		
	}
}