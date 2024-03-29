package com.goldenpond.stampbook.resources;

import java.math.BigDecimal;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.exception.StampBookException;
import com.goldenpond.stampbook.pojo.Item;
import com.goldenpond.stampbook.services.CatalogService;
import com.sun.jersey.api.NotFoundException;

@Component
@Produces(MediaType.APPLICATION_XML)
@Path("/stamps/{stampId}/{serialNumber}")
public class ItemResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	@Autowired CatalogService catalogService;

	@POST
	public Item postItem(
			@PathParam("stampId") long stampId,
			@PathParam("serialNumber") String serialNumber,
			@FormParam("name") String name,
			@FormParam("face") BigDecimal face,
			@FormParam("image") String image) {
		Item i = new Item();
		i.setSerialNumber(serialNumber);
		i.setName(name);
		i.setFace(face);
		i.setImage(image);
		try {
			catalogService.createItem(stampId, i);
		}
		catch (StampBookException e) {
			throw new WebApplicationException(e);
		}
		return i;
	}

	@GET
	public Item getItem(
			@PathParam("stampId") long stampId,
			@PathParam("serialNumber") String serialNumber) {
		Item i = catalogService.getItem(stampId, serialNumber);
		if (i == null) {
			throw new NotFoundException("Item not found");
		}
		return i;
	}

	@PUT
	public Response putItem(
			@PathParam("stampId") long stampId,
			@PathParam("serialNumber") String serialNumber,
			@FormParam("name") String name,
			@FormParam("face") BigDecimal face,
			@FormParam("image") String image) {
		Item i = new Item();
		i.setSerialNumber(serialNumber);
		i.setName(name);
		i.setFace(face);
		i.setImage(image);
		try {
			catalogService.updateItem(stampId, i);
		}
		catch (StampBookException e) {
			throw new WebApplicationException(e);
		}
		return Response.ok().build();
	}

	@DELETE
	public void deleteItem(
			@PathParam("stampId") long stampId,
			@PathParam("serialNumber") String serialNumber) {
		try {
			catalogService.deleteItem(stampId, serialNumber);
		}
		catch (StampBookException e) {
			throw new WebApplicationException(e);
		}
	}
}
