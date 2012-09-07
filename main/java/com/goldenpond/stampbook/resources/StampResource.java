package com.goldenpond.stampbook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.Stamp;
import com.sun.jersey.api.NotFoundException;

@Produces(MediaType.APPLICATION_XML)
public class StampResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	long stampId;

	StampResource(UriInfo uriInfo, Request request, long stampId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.stampId = stampId;
	}

	@POST
	public Stamp postStamp(String data) {
		Stamp s = new Stamp();
		s.setIssueNumber(data);
		CatalogManager.getInstance().add(s);
		return s;
	}

	@GET
	public Stamp getStamp() {
		Stamp s = CatalogManager.getInstance().get(stampId);
		if (s == null) {
			throw new NotFoundException("Stamp not found");
		}
		return s;
	}

	@PUT
	public void putStamp(String data) {
		Stamp stamp = getStamp();
		if (stamp != null) {
			stamp.setName(data);
			CatalogManager.getInstance().modify(stamp);
		}
		else {
			// it does not make sense to create a stamp using id if the stamp does not exist
			throw new NotFoundException("Stamp not found");
		}
	}

	@DELETE
	public void deleteStamp() {
		Stamp s = CatalogManager.getInstance().remove(stampId);
		if (s == null) {
			throw new NotFoundException("Stamp not found");
		}
	}

	@Path("{item}")
	public ItemResource getItemResource(@PathParam("item") String item) {
		return new ItemResource(uriInfo, request, stampId, item);
	}
}
