package com.goldenpond.stampbook.biz;

import java.util.List;

import com.goldenpond.stampbook.hibernate.StampDao;
import com.goldenpond.stampbook.hibernate.StampItemDao;
import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.StampItem;
import com.goldenpond.stampbook.pojo.Stamps;

public class CatalogManager {

	private static CatalogManager instance = new CatalogManager();

	private StampDao stampDao;
	private StampItemDao itemDao;

	private CatalogManager() {
		stampDao = new StampDao();
		itemDao = new StampItemDao();
	}

	public static CatalogManager getInstance() {
		return instance;
	}

	public void add(Stamp stamp) {
		stampDao.create(stamp);
	}

	public List<Stamp> listAll() {
		return stampDao.findAll();
	}

	public Stamp get(long stampId) {
		return stampDao.fetchById(stampId);
	}

	public void modify(Stamp stamp) {
		stampDao.update(stamp);
	}

	public void remove(Stamp stamp) {
		stampDao.delete(stamp);
	}

	public Stamp remove(Long stampId) {
		Stamp s = get(stampId);
		if (s == null) return null;
		stampDao.delete(s);
		return s;
	}

	public boolean has(Long stampId) {
		return get(stampId) != null;
	}

	public Stamps getStamps() {
		return new Stamps(listAll());
	}

	public StampItem getItem(long stampId, String serialNumber) {
		Stamp s = get(stampId);
		StampItem i = itemDao.fetch(s.getIssueNumber(), serialNumber);
		return i;
	}

	public void createItem(StampItem i) {
		if (i.getStamp() != null) {
			itemDao.create(i);
		}
	}

	public void updateItem(StampItem i) {
		itemDao.update(i);
	}

	public void deleteItem(StampItem i) {
		itemDao.delete(i);
	}
}
