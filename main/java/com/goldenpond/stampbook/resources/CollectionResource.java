package com.goldenpond.stampbook.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.pojo.Collection;
import com.goldenpond.stampbook.services.CollectionService;

@Path("users")
@Component
public class CollectionResource {

	@Autowired CollectionService collectionService;

	@POST
	@Path("getCollections")
	public List<Collection> getAll(@FormParam("userId") long userId) {
		return collectionService.getAll(userId);
	}

	@POST
	@Path("addCollection")
	public Collection add(
			@FormParam("userId") long userId,
			@FormParam("itemId") long itemId,
			@FormParam("postmarked") boolean postmarked,
			@FormParam("grade") int grade) {
		Collection c = new Collection();
		c.setUserId(userId);
		c.setItemId(itemId);
		c.setPostmarked(postmarked);
		c.setGrade(grade);
		collectionService.add(c);
		return c;
	}

	@POST
	@Path("removeCollection")
	public Collection remove(
			@FormParam("userId") long userId,
			@FormParam("itemId") long itemId) {
		return collectionService.remove(userId, itemId);
	}

	@POST
	@Path("getCollection")
	public Collection get(
			@FormParam("userId") long userId,
			@FormParam("itemId") long itemId) {
		return collectionService.get(userId, itemId);
	}
}
