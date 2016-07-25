package com.nutcracker.remote.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nutcracker.model.NutNote;
import com.nutcracker.model.NutUser;
import com.nutcracker.remote.NutcrackerFinderRemote;

@Stateless
public class NutcrackerFinderBean implements NutcrackerFinderRemote {

	@PersistenceContext(unitName="nutcracker-unit")
	private EntityManager entityManager;
		
	@Override
	@SuppressWarnings("unchecked")
	public List<NutNote> findUserGlobalNotes(String userLogin) {
		Query query = this.entityManager.createNamedQuery("noteGlobal");
		query.setParameter("userLogin", userLogin);
		
		return (List<NutNote>) query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<NutNote> findNotesWithDeadline(int userId) {
		Query query = this.entityManager.createNamedQuery("noteWithDeadline");
		
		query.setParameter("userId", userId);
		return (List<NutNote>) query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<NutNote> findNotesWithoutDeadline(int userId) {
		Query query = this.entityManager.createNamedQuery("noteWithoutDeadline");
		
		query.setParameter("userId", userId);
		return (List<NutNote>) query.getResultList();
	}

	@Override
	public List<NutNote> findNotesBeforeDate(int userId, LocalDate date) {
		List<NutNote> deadlineNotes = findNotesWithDeadline(userId);
		return deadlineNotes.stream()
				.filter(note -> note.getNoteDeadline().compareTo(date) < 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<NutNote> findNotesAfterDate(int userId, LocalDate date) {
		List<NutNote> deadlineNotes = findNotesWithDeadline(userId);
		return deadlineNotes.stream()
				.filter(note -> note.getNoteDeadline().compareTo(date) > 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<NutNote> findNotesBetweenDates(int userId, LocalDate fromDate, LocalDate toDate) {
		List<NutNote> deadlineNotes = findNotesWithDeadline(userId);
		return deadlineNotes.stream()
				.filter(note -> 
					note.getNoteDeadline().compareTo(fromDate) >= 0 &&
					note.getNoteDeadline().compareTo(toDate) <= 0)
				.collect(Collectors.toList());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NutNote> findNotesByMessage(int userId, String message) {
		Query query = this.entityManager.createNamedQuery("noteByMessage");
		
		String messageParameter = createStringWithWildcards(message).toLowerCase();
		query.setParameter("message", messageParameter);
		return (List<NutNote>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NutNote> findNotesByPlace(int userId, String place) {
		Query query = this.entityManager.createNamedQuery("noteByPlace");
		
		query.setParameter("userId", userId);
		String placeParameter = createStringWithWildcards(place);
		query.setParameter("placeName", placeParameter);
		
		return (List<NutNote>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NutNote> findNotesByCategory(int userId, String categoryName) {
		Query query = this.entityManager.createNamedQuery("noteByCategory");
		
		query.setParameter("userId", userId);
		String categoryParameter = createStringWithWildcards(categoryName).toLowerCase();
		query.setParameter("categoryName", categoryParameter);
		
		return (List<NutNote>) query.getResultList();
	}
	
	@Override
	public NutUser findUserByLogin(String userLogin) {
		Query query = this.entityManager.createNamedQuery("userByLogin");
		
		query.setParameter("userLogin", userLogin);
		return (NutUser) query.getSingleResult();
	}
	
	private String createStringWithWildcards(String message) {
		return new StringBuilder()
				.append("%")
				.append(message)
				.append("%")
				.toString();
	}

}
