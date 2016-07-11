package com.adrznej.nutcracker.controller.remote;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.adrznej.nutcracker.model.CategoryModel;
import com.adrznej.nutcracker.model.NoteModel;
import com.adrznej.nutcracker.model.UserModel;

@Remote
public interface UserControllerRemote {
	public void createUser(UserModel userModel);
	
	public void changeUserLogin(String oldUserLogin, String newUserLogin, String password);
	public void changeUserPassword(String userLogin, String oldPassword, String newPassword);
	
	public List<UserModel> getAllUsers();
	
	public void addUserNote(String userLogin, NoteModel note);
	public Set<NoteModel> getUserNotes(String userLogin);
	
	public void addUserCategory(String userLogin, CategoryModel category);
	public Set<CategoryModel> getUserCategories(String userLogin);
}
