package com.goldenpond.stampbook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.goldenpond.stampbook.biz.CatalogManager;
import com.goldenpond.stampbook.pojo.Stamp;

@Produces(MediaType.APPLICATION_XML)
public class StampResource {

	private String stampId;

	StampResource(String stampId) {
		this.stampId = stampId;
	}

	@GET
	public Stamp getStamp() {
		return CatalogManager.getInstance().get(Long.valueOf(stampId));
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
			System.out.println("No Stamp Id: " + stampId + " found");
		}
	}

	@DELETE
	public void deleteStamp() {
		Stamp stamp = getStamp();
		CatalogManager.getInstance().remove(stamp);
	}

	@Path("{item}")
	public ItemResource getItemResource(@PathParam("item") String item) {
		return new ItemResource(stampId, item);
	}
}
