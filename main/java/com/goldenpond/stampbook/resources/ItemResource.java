package com.goldenpond.stampbook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

import com.goldenpond.stampbook.pojo.StampItem;

public class ItemResource {

	private String stampId;
	private String serialNumber;

	ItemResource(String stampId, String item) {
		this.stampId = stampId;
		this.serialNumber = item;
	}

	@GET
	public StampItem getItem() {
		return null;
	}

	@PUT
	public Response putItem() {
		return null;
	}

	@DELETE
	public void deleteItem() {
		return;
	}
}
