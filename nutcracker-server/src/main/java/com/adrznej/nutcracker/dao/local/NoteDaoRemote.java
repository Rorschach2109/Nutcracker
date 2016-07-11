package com.adrznej.nutcracker.dao.local;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Remote;

import com.adrznej.nutcracker.model.NoteModel;

@Remote
public interface NoteDaoRemote {
	
	public NoteModel getNoteById(int noteId);
	
	public void updateNote(NoteModel note);
	
	public List<NoteModel> getGlobalAvailableNotes();
	
	public List<NoteModel> getNotesBeforeDate(String userLogin, LocalDateTime date);
	public List<NoteModel> getNotesAfterDate(String userLogin, LocalDateTime date);
	public List<NoteModel> getNotesBetweenDates(String userLogin, 
			LocalDateTime fromDate, LocalDateTime toDate);
	
	public List<NoteModel> getNotesByPlace(String userLogin, String place);
	
	public List<NoteModel> getNotesByMessage(String userLogin, String message);
}
