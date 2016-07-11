package com.adrznej.nutcracker.dao.local;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.adrznej.nutcracker.model.NoteModel;

@Remote
public interface NoteDaoRemote {
	public void insertNote(String userLogin, NoteModel note);
	public void editNote(NoteModel note);
	
	public Set<NoteModel> getUserNotes(String userLogin);
	
	public List<NoteModel> getGlobalAvailableNotes();
	
	public List<NoteModel> getUserNotesWithDeadline(String userLogin);
	public List<NoteModel> getUserNotesBeforeDate(String userLogin, LocalDateTime deadline);
	public List<NoteModel> getUserNotesAfterDate(String userLogin, LocalDateTime deadline);
	
	public List<NoteModel> getUserNotesByPlace(String userLogin, String place);
	
	public List<NoteModel> findNotesByContent(String content);
}
