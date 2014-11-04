package com.goldenpond.stampbook.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.goldenpond.stampbook.pojo.StampVO;
import com.goldenpond.stampbook.services.CatalogService;

public class CatalogServiceTest {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"services.xml", "daos.xml"});
	private CatalogService manager;
	private StampVO stampVO;

	private static final Long existingId = Long.valueOf(1);
	private static final Long nonExistentId = Long.valueOf(-1);
	private static final String existingIssueNumber = "2012-01";

	@Before
	public void setUp() {
		manager = ctx.getBean(CatalogService.class);
		stampVO = new StampVO();
		stampVO.setIssueNumber("2012-" + new Random().nextInt(100));
		stampVO.setName("a name");
	}

	@Test
	public void testAdd() {
		manager.add(stampVO);
		assertNotNull(stampVO.getId());
	}

	@Test(expected = HibernateException.class)
	public void testAddDuplicateIssueNumber() {
		stampVO.setIssueNumber(existingIssueNumber);
		manager.add(stampVO);
	}

	@Test
	public void testListAll() {
		List<StampVO> stampVOs = manager.listAll();
		assertNotNull(stampVOs);
		assertTrue(stampVOs.size() > 0);
	}

	@Test
	public void testGetExisting() {
		stampVO = manager.get(existingId);
		assertNotNull(stampVO);
	}

	@Test(expected = HibernateException.class)
	public void testGetNonExistant() {
		manager.get(nonExistentId);
	}

	@Test(expected = HibernateException.class)
	public void testRemoveCreated() {
		manager.add(stampVO);
		assertNotNull(stampVO.getId());
		manager.remove(stampVO);
		assertNotNull(stampVO.getId());
		manager.get(stampVO.getId());
	}

	@Test(expected = HibernateException.class)
	public void testRemoveNonExistent() {
		stampVO.setId(nonExistentId);
		manager.remove(stampVO);
	}

	@After
	public void tearDown() {
		manager = null;
		stampVO = null;
	}
}
