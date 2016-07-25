package com.nutcracker.remote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Remote;

import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutUser;

@Remote
public interface NutcrackerFinderRemote {

	public List<NutNote> findUserGlobalNotes(String userLogin);
	
	public List<NutNote> findNotesWithDeadline(int userId);
	public List<NutNote> findNotesWithoutDeadline(int userId);
	public List<NutNote> findNotesBeforeDate(int userId, LocalDate date);
	public List<NutNote> findNotesAfterDate(int userId, LocalDate date);
	public List<NutNote> findNotesBetweenDates(int userId, LocalDate fromDate, 
			LocalDate toDate);
	
	public List<NutNote> findNotesByMessage(int userId, String message);
	public List<NutNote> findNotesByPlace(int userId, String place);
	public List<NutNote> findNotesByCategory(int userId, String categoryName);
	
	public NutUser findUserByLogin(String userLogin);
}
