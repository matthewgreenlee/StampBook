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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.Stamp;
import com.sun.jersey.api.NotFoundException;

@Component
@Path("/stamps/{stampId}")
@Produces(MediaType.APPLICATION_XML)
public class StampResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	@Autowired CatalogManager catalogManager;

	@GET
	public Stamp getStamp(@PathParam("stampId") long stampId) {
		Stamp s = catalogManager.get(stampId);
		if (s == null) {
			throw new NotFoundException("Stamp not found");
		}
		return s;
	}

	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public Response putStamp(
			@PathParam("stampId") long stampId,
			@FormParam("issueNumber") String issueNumber,
			@FormParam("name") String name,
			@FormParam("type") String type,
			@FormParam("issueDate") Date issueDate,
			@FormParam("designedBy") String designedBy,
			@FormParam("printedBy") String printedBy) {
		Stamp s = getStamp(stampId);
		if (s != null) {
			if (issueNumber != null) s.setIssueNumber(issueNumber);
			if (name != null) s.setName(name);
			if (type != null) s.setType(type);
			if (issueDate != null) s.setIssueDate(issueDate);
			if (designedBy != null) s.setDesignedBy(designedBy);
			if (printedBy != null) s.setPrintedBy(printedBy);
			catalogManager.modify(s);
			return Response.ok().build();
		}
		else {
			// it does not make sense to create a stamp using id if the stamp does not exist
			throw new NotFoundException("Stamp not found");
		}
	}

	@DELETE
	public void deleteStamp(@PathParam("stampId") long stampId) {
		Stamp s = catalogManager.remove(stampId);
		if (s == null) {
			throw new NotFoundException("Stamp not found");
		}
	}
}
