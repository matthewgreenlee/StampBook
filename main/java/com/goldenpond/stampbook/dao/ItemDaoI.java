package com.goldenpond.stampbook.dao;

import java.util.List;

import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.Item;

public interface ItemDaoI {

	public abstract Item addToExistingStamp(Stamp stamp, Item item);

	public abstract List<Item> fetchItems(Stamp stamp);

	public abstract void removeFromExistingStamp(Stamp stamp, Item item);

	public abstract Item fetch(String issueNumber, String serialNumber);

}