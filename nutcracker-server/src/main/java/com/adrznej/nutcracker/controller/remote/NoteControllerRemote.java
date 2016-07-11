package com.adrznej.nutcracker.controller.remote;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Remote;

import com.adrznej.nutcracker.model.NoteModel;

@Remote
public interface NoteControllerRemote {
	public List<NoteModel> getGlobalAvailableNotes(String userLogin);
	
	public void updateNote(String userLogin, NoteModel note);
	
	public List<NoteModel> getNotesByPlace(String userLogin, String place);
	
	public List<NoteModel> getNotesByMessage(String userLogin, String message);
	
	public List<NoteModel> getNotesBeforeDate(String userLogin, LocalDateTime date);
	public List<NoteModel> getNotesAfterDate(String userLogin, LocalDateTime date);
	public List<NoteModel> getBotesBetweenDates(String userLogin, 
			LocalDateTime fromDate, LocalDateTime toDate);
}
