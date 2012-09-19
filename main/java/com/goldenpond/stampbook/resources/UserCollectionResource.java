package com.goldenpond.stampbook.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.goldenpond.stampbook.biz.CollectionService;
import com.goldenpond.stampbook.pojo.UserCollection;

@Path("users")
public class UserCollectionResource {

	@POST
	@Path("getCollections")
	public List<UserCollection> getCollections(@FormParam("userId") long userId) {
		return CollectionService.getInstance().getCollections(userId);
	}

	@POST
	@Path("addCollection")
	public UserCollection addCollection(@FormParam("userId") long userId, @FormParam("stampItemId") long stampItemId) {
		return CollectionService.getInstance().add(userId, stampItemId);
	}

	@POST
	@Path("removeCollection")
	public UserCollection removeCollection(@FormParam("userId") long userId, @FormParam("stampItemId") long stampItemId) {
		return CollectionService.getInstance().remove(userId, stampItemId);
	}
}
