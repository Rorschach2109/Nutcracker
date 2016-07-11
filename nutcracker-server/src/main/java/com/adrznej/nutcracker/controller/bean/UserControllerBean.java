package com.adrznej.nutcracker.controller.bean;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.adrznej.nutcracker.controller.remote.UserControllerRemote;
import com.adrznej.nutcracker.dao.local.UserDaoLocal;
import com.adrznej.nutcracker.model.CategoryModel;
import com.adrznej.nutcracker.model.NoteModel;
import com.adrznej.nutcracker.model.UserModel;

@Stateless
public class UserControllerBean implements UserControllerRemote {

	@Inject
	private UserDaoLocal userDao;
	
	@Override
	public void createUser(UserModel userModel) {
		if (this.userDao.userExist(userModel.getUserLogin())) {
			return;
		}
		
		this.userDao.createUser(userModel);
	}

	@Override
	public void changeUserLogin(String oldUserLogin, String newUserLogin, String password) {
		if (false == this.userDao.userExist(oldUserLogin)) {
			return;
		}
		
		if (this.userDao.userExist(newUserLogin)) {
			return;
		}
		
		UserModel user = this.userDao.getUserByLogin(oldUserLogin);
		user.setUserLogin(newUserLogin);
		
		this.userDao.updateUser(user);
	}

	@Override
	public void changeUserPassword(String userLogin, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public List<UserModel> getAllUsers() {
		return (List<UserModel>) this.userDao.getAllUsers();
	}

	@Override
	public void addUserNote(String userLogin, NoteModel note) {
		if (false == this.userDao.userExist(userLogin)) {
			return;
		}
		
		UserModel user = this.userDao.getUserByLogin(userLogin);
		
		Set<NoteModel> userNotes = user.getUserNotes();
		userNotes.add(note);
		user.setUserNotes(userNotes);
		
		note.setNoteOwner(user);
		
		this.userDao.updateUser(user);
	}

	@Override
	public void removeUserNote(String userLogin, NoteModel note) {
		if (false == this.userDao.userExist(userLogin)) {
			return;
		}
		
		UserModel user = this.userDao.getUserByLogin(userLogin);
		
		if (note.getNoteOwner().getUserModelId() != user.getUserModelId()) {
			return;
		}
		
		Set<NoteModel> userNotes = user.getUserNotes();
		userNotes.remove(note);
		user.setUserNotes(userNotes);
		
		this.userDao.updateUser(user);
	}

	@Override
	public Set<NoteModel> getUserNotes(String userLogin) {
		if (false == this.userDao.userExist(userLogin)) {
			return Collections.emptySet();
		}
		
		UserModel user = this.userDao.getUserByLogin(userLogin);
		return user.getUserNotes();
	}
	
	@Override
	public List<NoteModel> getUserNotesWithDeadline(String userLogin) {
		if (false == this.userDao.userExist(userLogin)) {
			return Collections.emptyList();
		}
		
		UserModel user = this.userDao.getUserByLogin(userLogin);
		return user.getUserNotes().stream()
				.filter(note -> note.getNoteDate().getNoteDeadline() != null)
				.sorted()
				.collect(Collectors.toList()) ;
	}
	
	@Override
	public void addUserCategory(String userLogin, CategoryModel category) {
		if (false == this.userDao.userExist(userLogin)) {
			return;
		}
		
		UserModel user = this.userDao.getUserByLogin(userLogin);
		
		Set<CategoryModel> userCategories = user.getUserCategories();
		userCategories.add(category);
		user.setUserCategories(userCategories);
		
		Set<UserModel> categoryUsers = category.getCategoryUsers();
		categoryUsers.add(user);
		category.setCategoryUsers(categoryUsers);
		
		this.userDao.updateUser(user);		
	}
	
	@Override
	public Set<CategoryModel> getUserCategories(String userLogin) {
		if (false == this.userDao.userExist(userLogin)) {
			return Collections.emptySet();
		}
		
		UserModel user = this.userDao.getUserByLogin(userLogin);
		
		return user.getUserCategories();
	}
}
