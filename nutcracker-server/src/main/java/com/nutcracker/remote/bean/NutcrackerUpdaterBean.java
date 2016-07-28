package com.nutcracker.remote.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutPlace;
import com.nutcracker.remote.NutcrackerUpdaterRemote;

@Stateless
public class NutcrackerUpdaterBean implements NutcrackerUpdaterRemote {

	@PersistenceContext(unitName="nutcracker-unit")
	private EntityManager entityManager;
	
	@Override
	public boolean updateNote(int userId, NutNote newNote) {		
		NutNote existingNote = this.entityManager.find(NutNote.class, newNote.getNoteId());
		if (null == existingNote) {
			return false;
		}
		
		Query query = this.entityManager.createNamedQuery("categoryByNameAndUserId");
		query.setParameter("categoryName", newNote.getNoteCategory().getCategoryName());
		query.setParameter("userId", userId);
		NutCategory newCategory = (NutCategory) query.getSingleResult();
		
		existingNote.setNoteTitle(newNote.getNoteTitle());
		existingNote.setNoteCategory(newCategory);
		existingNote.setNoteDeadline(newNote.getNoteDeadline());
		existingNote.setNotePlace(newNote.getNotePlace());
		existingNote.setGlobalAvailable(newNote.isGlobalAvailable());
		existingNote.setNoteMessage(newNote.getNoteMessage());
		
		this.entityManager.merge(existingNote);
		return true;
	}

	@Override
	public boolean updatePlace(int userId, NutPlace place) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCategory(int userId, NutCategory category) {
		NutCategory existingCategory = this.entityManager.find(NutCategory.class, category.getCategoryId());
		if (null == existingCategory) {
			return false;
		}
		
		existingCategory.setCategoryName(category.getCategoryName());
		
		this.entityManager.merge(existingCategory);
		return true;
	}

}
