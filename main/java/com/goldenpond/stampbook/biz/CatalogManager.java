package com.goldenpond.stampbook.biz;

import java.util.List;

import com.goldenpond.stampbook.exception.StampBookException;
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
		Stamp s = null;
		try {
			s = get(stampId);
		}
		catch (Exception e) {
			throw new StampBookException(e);
		}
		return s != null ? s.getItem(serialNumber) : null;
	}

	public void createItem(StampItem i) {
		if (i.getStamp() != null) {
			itemDao.create(i);
		}
	}

	public void createItem(long stampId, StampItem i) {
		Stamp s = get(stampId);
		if (s == null) throw new StampBookException("Stamp not found");
		if (s.hasItem(i)) {
			throw new StampBookException("Item already exists");
		} else {
			s.addItem(i);
		}
		stampDao.update(s);
	}

	public void updateItem(StampItem i) {
		try {
			itemDao.update(i);
		} 
		catch (Exception e) {
			throw new StampBookException(e);
		}
	}

	public void deleteItem(StampItem i) {
		itemDao.delete(i);
	}
}
