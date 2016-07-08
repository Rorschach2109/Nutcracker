package com.adrznej.nutcracker.dao.remote;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Remote;

import com.adrznej.nutcracker.model.CategoryModel;
import com.adrznej.nutcracker.model.NoteModel;
import com.adrznej.nutcracker.model.UserModel;

@Remote
public interface UserDaoRemote {

	public UserModel getUserById(int userId);
	public UserModel getUserByLogin(String login);
	public Collection<UserModel> getAllUsers();
	
	public void createUser(UserModel userModel);
	
	public void deleteUser(String userLogin);
	
	public Set<CategoryModel> getUserCategories(String userLogin);
	public Set<NoteModel> getUserNotes(String userLogin);
}
