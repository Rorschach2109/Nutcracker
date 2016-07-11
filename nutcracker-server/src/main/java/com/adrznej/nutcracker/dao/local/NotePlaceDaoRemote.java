package com.adrznej.nutcracker.dao.local;

import javax.ejb.Remote;

import com.adrznej.nutcracker.model.NotePlaceModel;

@Remote
public interface NotePlaceDaoRemote {

	public void insertPlace(NotePlaceModel placeModel);
	public void editPlace(NotePlaceModel placeModel);
}
