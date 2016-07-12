package com.nutcracker.remote;

import javax.ejb.Remote;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;

@Remote
public interface NutcrackerUpdaterRemote {

	public boolean updateNote(int userId, NutNote note);
	public boolean updatePlace(int userId, NutPlace place);
	public boolean updateCategory(int userId, NutCategory category);
}
