package com.adrznej.nutcracker.controller.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.adrznej.nutcracker.controller.remote.NoteControllerRemote;
import com.adrznej.nutcracker.dao.local.NoteDaoRemote;
import com.adrznej.nutcracker.model.NoteModel;

@Stateless
public class NoteControllerBean implements NoteControllerRemote {

	@Inject
	NoteDaoRemote noteDao;
	
	@Override
	public List<NoteModel> getGlobalAvailableNotes(String userLogin) {
		return this.noteDao.getGlobalAvailableNotes();
	}

	@Override
	public void updateNote(String userLogin, NoteModel note) {
		if (false == note.getNoteOwner().getUserLogin().equals(userLogin)) {
			return;
		}
		
		this.noteDao.updateNote(note);
	}

	@Override
	public List<NoteModel> getNotesByPlace(String userLogin, String place) {
		return this.noteDao.getNotesByPlace(userLogin, place);
	}

	@Override
	public List<NoteModel> getNotesByMessage(String userLogin, String message) {
		return this.noteDao.getNotesByMessage(userLogin, message);
	}

	@Override
	public List<NoteModel> getNotesBeforeDate(String userLogin, LocalDateTime date) {
		return this.noteDao.getNotesBeforeDate(userLogin, date);
	}

	@Override
	public List<NoteModel> getNotesAfterDate(String userLogin, LocalDateTime date) {
		return this.noteDao.getNotesAfterDate(userLogin, date);
	}

	@Override
	public List<NoteModel> getBotesBetweenDates(String userLogin, LocalDateTime fromDate, LocalDateTime toDate) {
		return this.noteDao.getNotesBetweenDates(userLogin, fromDate, toDate);
	}

}
