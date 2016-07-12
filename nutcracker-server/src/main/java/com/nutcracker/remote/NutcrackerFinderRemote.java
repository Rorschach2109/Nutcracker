package com.nutcracker.remote;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Remote;

import com.nutcracker.model.NutNote;

@Remote
public interface NutcrackerFinderRemote {

	public List<NutNote> findNotesWithDeadline(int userId);
	public List<NutNote> findNotesBeforeDate(int userId, LocalDateTime date);
	public List<NutNote> findNotesAfterDate(int userId, LocalDateTime date);
	public List<NutNote> findNotesInDate(int userId, LocalDateTime date);
	public List<NutNote> findNotesBetweenDates(int userId, LocalDateTime fromDate, 
			LocalDateTime toDate);
	
	public List<NutNote> findNotesByMessage(int userId, String message);
	public List<NutNote> findNotesByPlace(int userId, String place);
	public List<NutNote> findNotesByCategory(int userId, int categoryId);
}
