package com.goldenpond.stampbook.dao;

import java.util.List;

import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.StampItem;

public interface StampItemDaoI {

	public abstract StampItem addToExistingStamp(Stamp stamp, StampItem item);

	public abstract List<StampItem> fetchItems(Stamp stamp);

	public abstract void removeFromExistingStamp(Stamp stamp, StampItem item);

	public abstract StampItem fetch(String issueNumber, String serialNumber);

}