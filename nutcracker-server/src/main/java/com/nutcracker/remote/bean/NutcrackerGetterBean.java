package com.nutcracker.remote.bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;
import com.nutcracker.model.NutUser;
import com.nutcracker.remote.NutcrackerGetterRemote;

@Stateless
public class NutcrackerGetterBean implements NutcrackerGetterRemote {

	@PersistenceContext(unitName="nutcracker-unit")
	EntityManager entityManager;
	
	@Override
	public List<NutNote> getUserNotes(int userId) {
		NutUser user = this.entityManager.find(NutUser.class, userId);
		return user.getUserNotes().stream().collect(Collectors.toList());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NutNote> getOtherUsersNotes(int userId) {
		Query query = this.entityManager.createNamedQuery("noteOther");
		query.setParameter("userId", userId);
		
		return (List<NutNote>) query.getResultList();
	}

	@Override
	public List<NutNote> getAvailableNotes(int userId) {
		List<NutNote> userNotes = this.getUserNotes(userId);
		List<NutNote> otherNotes = this.getOtherUsersNotes(userId);
		
		return Stream
				.concat(userNotes.stream(), otherNotes.stream())
				.collect(Collectors.toList());
	}

	@Override
	public List<NutCategory> getUserCategories(int userId) {
		NutUser user = this.entityManager.find(NutUser.class, userId);
		return user.getUserCategories().stream().collect(Collectors.toList());
	}

	@Override
	public List<NutPlace> getPlaces(int userId) {
		NutUser user = this.entityManager.find(NutUser.class, userId);
		return user.getUserPlaces().stream().collect(Collectors.toList());
	}

}
