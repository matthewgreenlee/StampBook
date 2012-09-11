package com.goldenpond.stampbook.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
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

	@GET
	public Stamp getStamp() {
		Stamp s = CatalogManager.getInstance().get(stampId);
		if (s == null) {
			throw new NotFoundException("Stamp not found");
		}
		return s;
	}

	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public Response putStamp(@FormParam("issueNumber") String issueNumber,
			@FormParam("name") String name,
			@FormParam("issueDate") Date issueDate,
			@FormParam("designedBy") String designedBy,
			@FormParam("printedBy") String printedBy) {
		Stamp s = getStamp();
		if (s != null) {
			s.setIssueNumber(issueNumber);
			s.setName(name);
			s.setIssueDate(issueDate);
			s.setDesignedBy(designedBy);
			s.setPrintedBy(printedBy);
			CatalogManager.getInstance().modify(s);
			return Response.ok().build();
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
