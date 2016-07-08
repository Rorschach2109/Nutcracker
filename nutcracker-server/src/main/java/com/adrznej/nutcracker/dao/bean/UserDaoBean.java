package com.adrznej.nutcracker.dao.bean;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adrznej.nutcracker.dao.remote.UserDaoRemote;
import com.adrznej.nutcracker.model.CategoryModel;
import com.adrznej.nutcracker.model.NoteModel;
import com.adrznej.nutcracker.model.UserModel;

@Stateless
public class UserDaoBean implements UserDaoRemote {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UserModel getUserById(int userId) {
		return this.entityManager.find(UserModel.class, userId);
	}

	@Override
	public UserModel getUserByLogin(String userLogin) {
		Query query = this.entityManager.createNamedQuery("getUserByLogin");
		query.setParameter("userLogin", userLogin);
		return (UserModel) query.getSingleResult();
	}
	
	@Override	
	@SuppressWarnings("unchecked")
	public Collection<UserModel> getAllUsers() {
		Query query = this.entityManager.createNamedQuery("getAllUsers");
		return (List<UserModel>) query.getResultList();
	}

	@Override
	public void createUser(UserModel userModel) {
		this.entityManager.persist(userModel);		
	}

	@Override
	public void deleteUser(String userLogin) {
		this.entityManager.remove(this.getUserByLogin(userLogin));
	}

	@Override
	public Set<CategoryModel> getUserCategories(String userLogin) {
		return this.getUserByLogin(userLogin).getUserCategories();
	}

	@Override
	public Set<NoteModel> getUserNotes(String userLogin) {
		return this.getUserByLogin(userLogin).getUserNotes();		
	}

}
