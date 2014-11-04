package com.goldenpond.stampbook.dao;

import java.util.List;

import com.goldenpond.stampbook.pojo.StampVO;
import com.goldenpond.stampbook.pojo.Item;

public interface ItemDao {

	public abstract Item addToExistingStamp(StampVO stampVO, Item item);

	public abstract List<Item> fetchItems(StampVO stampVO);

	public abstract void removeFromExistingStamp(StampVO stampVO, Item item);

	public abstract Item fetch(String issueNumber, String serialNumber);

}