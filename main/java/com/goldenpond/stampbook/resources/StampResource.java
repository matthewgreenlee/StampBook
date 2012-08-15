package com.goldenpond.stampbook.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.goldenpond.stampbook.hibernate.StampDao;
import com.goldenpond.stampbook.hibernate.StampItemDao;
import com.goldenpond.stampbook.pojo.Stamp;

@Path("{stamp}")
public class StampResource {

	private StampDao stampDao;
	private StampItemDao itemDao;

	public StampResource() {
		stampDao = new StampDao();
		itemDao = new StampItemDao();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Stamp get(@PathParam("stamp") Long stampId) {
		Stamp stamp = new Stamp();
		stamp.setId(stampId);
		return stampDao.fetch(stamp);
	}
}
