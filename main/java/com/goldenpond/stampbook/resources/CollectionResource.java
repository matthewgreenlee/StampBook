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
	public List<Collection> getCollections(@FormParam("userId") long userId) {
		return collectionService.getCollections(userId);
	}

	@POST
	@Path("addCollection")
	public Collection addCollection(@FormParam("userId") long userId, @FormParam("itemId") long itemId) {
		return collectionService.add(userId, itemId);
	}

	@POST
	@Path("removeCollection")
	public Collection removeCollection(@FormParam("userId") long userId, @FormParam("itemId") long itemId) {
		return collectionService.remove(userId, itemId);
	}
}
