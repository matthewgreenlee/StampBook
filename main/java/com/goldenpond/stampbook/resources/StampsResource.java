package com.goldenpond.stampbook.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.Stamp;
import com.goldenpond.stampbook.pojo.Stamps;

@Path("/stamps")
@Produces(MediaType.APPLICATION_XML)
@Component
public class StampsResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	@Autowired CatalogManager catalogManager;


	@GET
	public Stamps getStamps() {
		return catalogManager.getStamps();
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Stamp postStamp(@FormParam("issueNumber") String issueNumber,
			@FormParam("name") String name,
			@FormParam("type") String type,
			@FormParam("issueDate") Date issueDate,
			@FormParam("designedBy") String designedBy,
			@FormParam("printedBy") String printedBy) {
		Stamp s = new Stamp();
		s.setIssueNumber(issueNumber);
		s.setName(name);
		s.setType(type);
		s.setIssueDate(issueDate);
		s.setDesignedBy(designedBy);
		s.setPrintedBy(printedBy);
		catalogManager.add(s);
		return s;
	}
}
