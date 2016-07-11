package com.adrznej.nutcracker.dao.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adrznej.nutcracker.dao.local.NoteDaoRemote;
import com.adrznej.nutcracker.model.NoteModel;

@Stateless
public class NoteDaoBean implements NoteDaoRemote {

	@PersistenceContext(unitName="nutcracker-unit")
	EntityManager entityManager;

	@Override
	public NoteModel getNoteById(int noteId) {
		return this.entityManager.find(NoteModel.class, noteId);
	}
	
	@Override
	public void updateNote(NoteModel note) {
		NoteModel oldNote = this.getNoteById(note.getNoteModelId());
		if (null == oldNote) {
			return;
		}
		
		oldNote.setGlobalAvailable(note.isGlobalAvailable());
		oldNote.setNoteCategory(note.getNoteCategory());
		oldNote.setMessage(note.getMessage());
		oldNote.setNotePlace(note.getNotePlace());
		
		this.entityManager.refresh(oldNote);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NoteModel> getGlobalAvailableNotes() {
		Query query = this.entityManager.createNamedQuery("getGlobalAvailableNotes");
		return (List<NoteModel>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NoteModel> getNotesBeforeDate(String userLogin, LocalDateTime date) {
		Query query = this.entityManager.createNamedQuery("getNotesBefore");
		return (List<NoteModel>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NoteModel> getNotesAfterDate(String userLogin, LocalDateTime date) {
		Query query = this.entityManager.createNamedQuery("getNotesAfter");
		return (List<NoteModel>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NoteModel> getNotesBetweenDates(String userLogin, 
			LocalDateTime fromDate, LocalDateTime toDate) {
		Query query = this.entityManager.createNamedQuery("getNotesBetween");
		return (List<NoteModel>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NoteModel> getNotesByPlace(String userLogin, String place){
		Query query = this.entityManager.createNamedQuery("getNotesByPlace");
		return (List<NoteModel>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NoteModel> getNotesByMessage(String userLogin, String message) {
		Query query = this.entityManager.createNamedQuery("getNotesByMessage");
		return (List<NoteModel>) query.getResultList();
	}

}
