package com.nutcracker.remote.bean;

import java.util.List;

import javax.ejb.Stateless;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;
import com.nutcracker.remote.NutcrackerGetterRemote;

@Stateless
public class NutcrackerGetterBean implements NutcrackerGetterRemote {

	@Override
	public List<NutNote> getUserNotes(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> getOtherUsersNotes(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> getAvailableNotes(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutCategory> getUserCategories(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutPlace> getPlaces(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
