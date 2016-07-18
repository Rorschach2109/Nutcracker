package com.nutcracker.model.query;

public final class NutNoteNamedQuery {

	public static final String NOTE_WITH_DEADLINE = "SELECT note FROM NutNote note JOIN FETCH note.noteOwner AS owner WHERE (owner.userId = :userId OR note.globalAvailable = 'T') AND note.noteDeadline IS NOT NULL";
	public static final String NOTE_BY_MESSAGE = "SELECT note FROM NutNote note WHERE LOWER(note.noteMessage) LIKE :message";
	public static final String NOTE_BY_CATEGORY = "SELECT note FROM NutNote note JOIN FETCH note.noteOwner AS owner JOIN FETCH note.noteCategory AS category WHERE (owner.userId = :userId OR note.globalAvailable = 'T') AND LOWER(category.categoryName) LIKE :categoryName";
	public static final String NOTE_BY_PLACE = "SELECT note FROM NutNote note JOIN FETCH note.noteOwner AS owner JOIN FETCH note.notePlace AS place WHERE (owner.userId = :userId OR note.globalAvailable = 'T') AND (LOWER(place.country) LIKE :placeName OR LOWER(place.city) LIKE :placeName OR LOWER(place.address) LIKE :placeName)";
	public static final String NOTE_OTHER = "SELECT note FROM NutNote note JOIN FETCH note.noteOwner AS owner WHERE note.globalAvailable = 'T' AND owner.userId != :userId";
	
	private NutNoteNamedQuery() {
		
	}
}
