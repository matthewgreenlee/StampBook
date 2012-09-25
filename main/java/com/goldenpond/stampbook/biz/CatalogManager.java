package com.goldenpond.stampbook.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.hibernate.ItemDaoImpl;
import com.goldenpond.stampbook.dao.hibernate.StampDaoImpl;
import com.goldenpond.stampbook.exception.StampBookException;
import com.goldenpond.stampbook.pojo.Item;
import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.Stamps;

@Component
@Scope("singleton")
public class CatalogManager {

	@Autowired(required=true) StampDaoImpl stampDaoImpl;
	@Autowired(required=true) ItemDaoImpl itemDaoImpl;

	public void add(Stamp stamp) {
		stampDaoImpl.create(stamp);
	}

	public List<Stamp> listAll() {
		return stampDaoImpl.findAll();
	}

	public Stamp get(long stampId) {
		return stampDaoImpl.fetchById(stampId);
	}

	public void modify(Stamp stamp) {
		stampDaoImpl.update(stamp);
	}

	public void remove(Stamp stamp) {
		stampDaoImpl.delete(stamp);
	}

	public Stamp remove(Long stampId) {
		Stamp s = get(stampId);
		if (s == null) return null;
		stampDaoImpl.delete(s);
		return s;
	}

	public Stamps getStamps() {
		return new Stamps(listAll());
	}

	public Item getItem(long stampId, String serialNumber) {
		Stamp s = null;
		try {
			s = get(stampId);
		}
		catch (Exception e) {
			throw new StampBookException(e);
		}
		return s != null ? s.getItem(serialNumber) : null;
	}

	public void createItem(long stampId, Item i) {
		Stamp s = get(stampId);
		if (s == null) 
			throw new StampBookException("Stamp not found");
		if (s.hasItem(i)) {
			throw new StampBookException("Item already exists");
		} else {
			s.addItem(i);
		}
		stampDaoImpl.update(s);
	}

	public void updateItem(long stampId, Item item) {
		Stamp s = get(stampId);
		if (s == null) 
			throw new StampBookException("Stamp not found");
		if (s.hasItem(item)) {
			Item i = s.getItem(item.getSerialNumber());
			if (item.getName() != null) i.setName(item.getName());
			if (item.getFace() != null) i.setFace(item.getFace());
			if (item.getImage() != null) i.setImage(item.getImage());
		} else {
			throw new StampBookException("Item not found");
		}
		stampDaoImpl.update(s);
	}

	public void deleteItem(long stampId, String serialNumber) {
		Item i = getItem(stampId, serialNumber);
		if (i == null)
			throw new StampBookException("Item not found");
		itemDaoImpl.delete(i);
	}
}
