package com.nutcracker.remote;

import javax.ejb.Remote;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;
import com.nutcracker.model.NutUser;

@Remote
public interface NutcrackerSetterRemote {
	
	public int insertUser(NutUser user);
	
	public boolean insertCategory(int userId, NutCategory category);
	public boolean insertNote(int userId, NutNote note);
	public boolean insertNote(int userId, NutNote note, int categoryId, int placeId);
	public boolean insertPlace(int userId, NutPlace place);
}
