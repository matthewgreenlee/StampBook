package com.goldenpond.stampbook.biz;

import java.util.List;

import com.goldenpond.stampbook.hibernate.StampDao;
import com.goldenpond.stampbook.hibernate.StampItemDao;
import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.StampItem;

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
		if (stamp.getItems().size() > 0) {
			// create stamp items accordingly
			for (StampItem item : stamp.getItems()) {
				itemDao.addToExistingStamp(stamp, item.getSerialNumber());
			}
		}
	}

	public List<Stamp> listAll() {
		return stampDao.findAll();
	}

	// get stamp also all attached items
	public Stamp get(Long stampId) {
		Stamp stamp = new Stamp();
		stamp.setId(stampId);
		return stampDao.fetch(stamp);
	}

	public void modify(Stamp stamp) {
		stampDao.update(stamp);
	}

	public void remove(Stamp stamp) {
		stampDao.delete(stamp);
	}
}
