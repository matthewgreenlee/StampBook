package com.goldenpond.stampbook.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.Stamps;

@Path("/stamps")
@Produces(MediaType.APPLICATION_XML)
public class StampsResource {

	@Context UriInfo uriInfo;
	@Context Request request;

	@Path("{stampId}")
	public StampResource getStampResource(@PathParam("stampId") long stampId) {
		return new StampResource(uriInfo, request, stampId);
	}

	@GET
	public Stamps getStamps() {
		return CatalogManager.getInstance().getStamps();
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Stamp postStamp(@FormParam("issueNumber") String issueNumber,
			@FormParam("name") String name,
			@FormParam("issueDate") Date issueDate,
			@FormParam("designedBy") String designedBy,
			@FormParam("printedBy") String printedBy) {
		Stamp s = new Stamp();
		s.setIssueNumber(issueNumber);
		s.setName(name);
		s.setIssueDate(issueDate);
		s.setDesignedBy(designedBy);
		s.setPrintedBy(printedBy);
		CatalogManager.getInstance().add(s);
		return s;
	}
}
