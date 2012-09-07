package com.goldenpond.stampbook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	long stampId;
	String serialNumber;

	ItemResource(UriInfo uriInfo, Request request, long stampId, String item) {
		this.stampId = stampId;
		this.serialNumber = item;
	}

	@POST
	public StampItem postItem(String data) {
		StampItem i = new StampItem();
		i.setSerialNumber(serialNumber);
		i.setName(data);
		CatalogManager.getInstance().createItem(i);
		return i;
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
	public Response putItem(String data) {
		StampItem i = getItem();
		i.setName(data);
		CatalogManager.getInstance().updateItem(i);
		return Response.noContent().build();
	}

	@DELETE
	public void deleteItem() {
		StampItem i = getItem();
		CatalogManager.getInstance().deleteItem(i);
		return;
	}
}
