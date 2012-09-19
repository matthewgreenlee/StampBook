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

	public void createItem(long stampId, StampItem i) {
		Stamp s = get(stampId);
		if (s == null) 
			throw new StampBookException("Stamp not found");
		if (s.hasItem(i)) {
			throw new StampBookException("Item already exists");
		} else {
			s.addItem(i);
		}
		stampDao.update(s);
	}

	public void updateItem(long stampId, StampItem item) {
		Stamp s = get(stampId);
		if (s == null) 
			throw new StampBookException("Stamp not found");
		if (s.hasItem(item)) {
			StampItem i = s.getItem(item.getSerialNumber());
			if (item.getName() != null) i.setName(item.getName());
			if (item.getFace() != null) i.setFace(item.getFace());
			if (item.getImage() != null) i.setImage(item.getImage());
		} else {
			throw new StampBookException("Item not found");
		}
		stampDao.update(s);
	}

	public void deleteItem(long stampId, String serialNumber) {
		StampItem i = getItem(stampId, serialNumber);
		if (i == null)
			throw new StampBookException("Item not found");
		itemDao.delete(i);
	}
}
