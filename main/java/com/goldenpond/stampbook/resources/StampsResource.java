package com.goldenpond.stampbook.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/stamps")
public class StampsResource {

	@Context UriInfo uriInfo;
	@Context Request request;

	@Path("{stampId}")
	public StampResource getStampResource(@PathParam("stampId") String stampId) {
		System.out.println("go thru here no matter which http method is used");
		return new StampResource(stampId);
	}
}
