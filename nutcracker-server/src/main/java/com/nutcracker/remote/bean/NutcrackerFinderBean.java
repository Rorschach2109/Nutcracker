package com.nutcracker.remote.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;

import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerFinderRemote;

@Stateless
public class NutcrackerFinderBean implements NutcrackerFinderRemote {

	@Override
	public List<NutNote> findNotesWithDeadline(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> findNotesBeforeDate(int userId, LocalDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> findNotesAfterDate(int userId, LocalDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> findNotesInDate(int userId, LocalDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> findNotesBetweenDates(int userId, LocalDateTime fromDate, LocalDateTime toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> findNotesByMessage(int userId, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> findNotesByPlace(int userId, String place) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutNote> findNotesByCategory(int userId, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
