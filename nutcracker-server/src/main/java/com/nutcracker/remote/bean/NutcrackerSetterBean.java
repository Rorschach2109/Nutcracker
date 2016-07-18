package com.nutcracker.remote.bean;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;
import com.nutcracker.model.NutUser;
import com.nutcracker.remote.NutcrackerSetterRemote;

@Stateless
public class NutcrackerSetterBean implements NutcrackerSetterRemote {

	@PersistenceContext(unitName="nutcracker-unit")
	EntityManager entityManager;

	@Override
	public int inserUser(NutUser user) {
		//TODO: check if user exists
		this.entityManager.persist(user);
		this.entityManager.flush();
		return user.getUserId();
	}
	
	@Override
	public boolean insertCategory(int userId, NutCategory category) {
		NutUser currentUser = this.entityManager.find(NutUser.class, userId);
		
		if (null == currentUser) {
			return false;
		}
	
		Set<NutCategory> userCategories = currentUser.getUserCategories();
		if (userCategories.contains(category)) {
			return false;
		}
		
		userCategories.add(category);
		currentUser.setUserCategories(userCategories);
		
		this.entityManager.merge(currentUser);
		return true;
	}

	@Override
	public boolean insertNote(int userId, NutNote note, int categoryId, int placeId) {
		NutUser currentUser = this.entityManager.find(NutUser.class, userId);
		
		if (null == currentUser) {
			return false;
		}
		
		NutCategory category = this.entityManager.find(NutCategory.class, categoryId);
		NutPlace place = this.entityManager.find(NutPlace.class, placeId);
		
		note.setNoteCategory(category);
		note.setNotePlace(place);
		
		Set<NutNote> userNotes = currentUser.getUserNotes();
		if (userNotes.contains(note)) {
			return false;
		}
		
		userNotes.add(note);
		currentUser.setUserNotes(userNotes);
		
		this.entityManager.merge(currentUser);		
		return true;
	}

	@Override
	public boolean insertPlace(int userId, NutPlace place) {
		NutUser currentUser = this.entityManager.find(NutUser.class, userId);
		
		if (null == currentUser) {
			return false;
		}
		
		Set<NutPlace> userPlaces = currentUser.getUserPlaces();
		if (userPlaces.contains(place)) {
			return false;
		}
		
		userPlaces.add(place);
		currentUser.setUserPlaces(userPlaces);
		
		this.entityManager.merge(currentUser);		
		return true;
	}
}
