package com.adrznej.nutcracker.dao.local;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Local;

import com.adrznej.nutcracker.model.CategoryModel;
import com.adrznej.nutcracker.model.UserModel;

@Local
public interface UserDaoLocal {
	public UserModel getUserById(int userId);
	public UserModel getUserByLogin(String userLogin);
	public Collection<UserModel> getAllUsers();
	
	public boolean userExist(String userLogin);
	
	public void createUser(UserModel userModel);
	public void updateUser(UserModel userModel);
	public void deleteUser(String userLogin);
	
	public Set<CategoryModel> getUserCategories(String userLogin);
}
