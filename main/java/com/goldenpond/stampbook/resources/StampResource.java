package com.goldenpond.stampbook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.goldenpond.stampbook.hibernate.StampDao;
import com.goldenpond.stampbook.pojo.Stamp;

@Produces(MediaType.APPLICATION_XML)
public class StampResource {

	private StampDao stampDao;
	private String stampId;

	StampResource(String stampId) {
		stampDao = new StampDao();
		this.stampId = stampId;
	}

	@GET
	public Stamp getStamp() {
		Stamp stamp = new Stamp();
		stamp.setId(Long.valueOf(stampId));
		return stampDao.fetch(stamp);
	}

	@PUT
	public void putStamp(String data) {
		Stamp stamp = getStamp();
		if (stamp != null) {
			stamp.setName(data);
			stampDao.update(stamp);
		}
		else {
			// it does not make sense to create a stamp using id if the stamp does not exist
			System.out.println("No Stamp Id: " + stampId + " found");
		}
	}

	@DELETE
	public void deleteStamp() {
		Stamp stamp = getStamp();
		stampDao.delete(stamp);
	}

	@Path("{item}")
	public ItemResource getItemResource(@PathParam("item") String item) {
		return new ItemResource(stampId, item);
	}
}
