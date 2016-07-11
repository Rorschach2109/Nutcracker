package com.adrznej.nutcracker.dao.bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import com.adrznej.nutcracker.dao.local.NoteDaoRemote;
import com.adrznej.nutcracker.model.NoteModel;

@Stateless
public class NoteDaoBean implements NoteDaoRemote {

	@Override
	public void insertNote(String userLogin, NoteModel note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editNote(NoteModel note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<NoteModel> getUserNotes(String userLogin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteModel> getGlobalAvailableNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteModel> getUserNotesWithDeadline(String userLogin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteModel> getUserNotesBeforeDate(String userLogin, LocalDateTime deadline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteModel> getUserNotesAfterDate(String userLogin, LocalDateTime deadline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteModel> getUserNotesByPlace(String userLogin, String place) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteModel> findNotesByContent(String content) {
		// TODO Auto-generated method stub
		return null;
	}

}
