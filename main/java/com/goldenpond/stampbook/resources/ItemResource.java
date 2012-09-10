package com.goldenpond.stampbook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.Stamp;
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
	public StampItem postItem(@FormParam("name") String name,
			@FormParam("face") long face) {
		StampItem i = new StampItem();
		i.setSerialNumber(this.serialNumber);
		i.setName(name);
		i.setFace(face);
		Stamp s = CatalogManager.getInstance().get(stampId);
		i.setStamp(s);
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
	public Response putItem(@FormParam("name") String name, @FormParam("face") long face) {
		StampItem i = getItem();
		i.setName(name);
		i.setFace(face);
		CatalogManager.getInstance().updateItem(i);
		return Response.ok().build();
	}

	@DELETE
	public void deleteItem() {
		StampItem i = getItem();
		CatalogManager.getInstance().deleteItem(i);
		return;
	}
}
