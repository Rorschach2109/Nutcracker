package com.nutcracker.remote.bean;

import javax.ejb.Stateless;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;
import com.nutcracker.remote.NutcrackerUpdaterRemote;

@Stateless
public class NutcrackerUpdaterBean implements NutcrackerUpdaterRemote {

	@Override
	public boolean updateNote(int userId, NutNote note) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePlace(int userId, NutPlace place) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCategory(int userId, NutCategory category) {
		// TODO Auto-generated method stub
		return false;
	}

}
