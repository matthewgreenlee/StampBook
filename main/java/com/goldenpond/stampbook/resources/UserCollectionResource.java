package com.goldenpond.stampbook.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldenpond.stampbook.biz.CollectionService;
import com.goldenpond.stampbook.pojo.UserCollection;

@Path("users")
@Component
public class UserCollectionResource {

	@Autowired CollectionService collectionService;

	@POST
	@Path("getCollections")
	public List<UserCollection> getCollections(@FormParam("userId") long userId) {
		return collectionService.getCollections(userId);
	}

	@POST
	@Path("addCollection")
	public UserCollection addCollection(@FormParam("userId") long userId, @FormParam("stampItemId") long stampItemId) {
		return collectionService.add(userId, stampItemId);
	}

	@POST
	@Path("removeCollection")
	public UserCollection removeCollection(@FormParam("userId") long userId, @FormParam("stampItemId") long stampItemId) {
		return collectionService.remove(userId, stampItemId);
	}
}
