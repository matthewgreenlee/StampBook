package com.goldenpond.stampbook.resources;

import java.math.BigDecimal;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.exception.StampBookException;
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
			@FormParam("face") BigDecimal face, @FormParam("image") String image) {
		StampItem i = new StampItem();
		i.setSerialNumber(this.serialNumber);
		i.setName(name);
		i.setFace(face);
		i.setImage(image);
//		Stamp s = CatalogManager.getInstance().get(stampId);
//		i.setStamp(s);
//		CatalogManager.getInstance().createItem(i);
		CatalogManager.getInstance().createItem(stampId, i);
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
	public Response putItem(@FormParam("name") String name,
			@FormParam("face") BigDecimal face, @FormParam("image") String image) {
		StampItem i = getItem();
		if (name != null) i.setName(name);
		if (face != null) i.setFace(face);
		if (image != null) i.setImage(image);
		try {
			CatalogManager.getInstance().updateItem(i);
		}
		catch (StampBookException e) {
			throw new WebApplicationException(e);
		}
		return Response.ok().build();
	}

	@DELETE
	public void deleteItem() {
		StampItem i = getItem();
		CatalogManager.getInstance().deleteItem(i);
		return;
	}
}
