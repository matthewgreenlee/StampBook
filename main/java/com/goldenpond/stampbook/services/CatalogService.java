package com.goldenpond.stampbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.dao.hibernate.ItemDaoImpl;
import com.goldenpond.stampbook.dao.hibernate.StampDaoImpl;
import com.goldenpond.stampbook.domain.Stamp;
import com.goldenpond.stampbook.exception.StampBookException;
import com.goldenpond.stampbook.pojo.Item;
import com.goldenpond.stampbook.pojo.StampVO;
import com.goldenpond.stampbook.pojo.Stamps;

@Component
@Scope("singleton")
public class CatalogService {

	@Autowired(required=true) StampDaoImpl stampDaoImpl;
	@Autowired(required=true) ItemDaoImpl itemDaoImpl;

	public void addStamp(StampVO vo) {
		Stamp s = new Stamp(vo);
		s.save();
	}

	public void add(StampVO stampVO) {
		if (get(stampVO.getIssueNumber()) != null) {
			throw new StampBookException("IssueNumber already exists");
		}
		try {
			stampDaoImpl.create(stampVO);
		}
		catch (Exception e) {
			throw new StampBookException(e);
		}
	}

	public List<StampVO> listAll() {
		return stampDaoImpl.findAll();
	}

	public StampVO get(long stampId) {
		return stampDaoImpl.fetchById(stampId);
	}

	public StampVO get(String issueNumber) {
		return stampDaoImpl.fetchByIssueNumber(issueNumber);
	}

	public void modify(StampVO stampVO) {
		stampDaoImpl.update(stampVO);
	}

	public void remove(StampVO stampVO) {
		stampDaoImpl.delete(stampVO);
	}

	public StampVO remove(Long stampId) {
		StampVO s = get(stampId);
		if (s == null) return null;
		stampDaoImpl.delete(s);
		return s;
	}

	public Stamps getStamps() {
		return new Stamps(listAll());
	}

	public Item getItem(long stampId, String serialNumber) {
		StampVO s = null;
		try {
			s = get(stampId);
		}
		catch (Exception e) {
			throw new StampBookException(e);
		}
		return s != null ? s.getItem(serialNumber) : null;
	}

	public void createItem(long stampId, Item i) {
		StampVO s = get(stampId);
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
		StampVO s = get(stampId);
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
