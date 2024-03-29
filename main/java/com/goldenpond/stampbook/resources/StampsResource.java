package com.goldenpond.stampbook.resources;

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

import com.goldenpond.stampbook.pojo.StampVO;
import com.goldenpond.stampbook.pojo.Stamps;
import com.goldenpond.stampbook.services.CatalogService;

@Path("/stamps")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class StampsResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	@Autowired CatalogService catalogService;


	@GET
	public Stamps getStamps() {
		return catalogService.getStamps();
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public StampVO postStamp(
			@FormParam("issueNumber") String issueNumber,
			@FormParam("name") String name,
			@FormParam("type") String type) {
		StampVO s = new StampVO();
		s.setIssueNumber(issueNumber);
		s.setName(name);
		s.setType(type);
		// catalogService.add(s);
		catalogService.addStamp(s);
		return s;
	}
}
