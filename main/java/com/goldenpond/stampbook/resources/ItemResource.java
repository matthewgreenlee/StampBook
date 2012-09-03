package com.goldenpond.stampbook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.StampItem;
import com.sun.jersey.api.NotFoundException;

public class ItemResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	String stampId;
	String serialNumber;

	ItemResource(UriInfo uriInfo, Request request, String stampId, String item) {
		this.stampId = stampId;
		this.serialNumber = item;
	}

	@GET
	public StampItem getItem() {
		StampItem i = CatalogManager.getInstance().getItem(stampId, serialNumber);
		if (i == null) {
			throw new NotFoundException("Item not found");
		}
		return i;
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
