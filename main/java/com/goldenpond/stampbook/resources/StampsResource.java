package com.goldenpond.stampbook.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.Stamps;

@Path("/stamps")
public class StampsResource {

	@Context UriInfo uriInfo;
	@Context Request request;

	@Path("{stampId}")
	public StampResource getStampResource(@PathParam("stampId") String stampId) {
		return new StampResource(stampId);
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Stamps getStamps() {
		return new Stamps(CatalogManager.getInstance().listAll());
	}
}
