package com.nutcracker.remote;

import javax.ejb.Remote;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;

@Remote
public interface NutcrackerSetterRemote {
	
	public boolean insertCategory(int userId, NutCategory category);
	public boolean insertNote(int userId, NutNote note);
	public boolean insertPlace(int userId, NutPlace place);
}
