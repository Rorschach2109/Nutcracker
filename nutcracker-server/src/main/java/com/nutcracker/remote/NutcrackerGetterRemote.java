package com.nutcracker.remote;

import java.util.List;

import javax.ejb.Remote;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;

@Remote
public interface NutcrackerGetterRemote {

	public boolean userExist(String userLogin);
	
	public List<String> getUsersLogins();
	
	public String getUserLogin(int userId);
	
	public List<NutNote> getUserNotes(int userId);
	public List<NutNote> getOtherUsersNotes(int userId);
	public List<NutNote> getAvailableNotes(int userId);
	
	public List<NutCategory> getUserCategories(int userId);
	
	public List<NutPlace> getPlaces(int userId);
}
