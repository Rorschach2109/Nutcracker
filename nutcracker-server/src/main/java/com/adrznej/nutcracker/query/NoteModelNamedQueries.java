package com.adrznej.nutcracker.query;

public final class NoteModelNamedQueries {

	public static final String GET_GLOBAL_AVAILABLE_NOTES = "SELECT note FROM NoteModel AS note WHERE note.globalAvailable = T";
	public static final String GET_NOTES_BY_PLACE = "SELECT note FROM NoteModel AS note";
	public static final String GET_NOTES_BY_MESSAGE = "SELECT note FROM NoteModel AS note";
	public static final String GET_NOTES_BEFORE_DATE = "SELECT note FROM NoteModel AS note";
	public static final String GET_NOTES_AFTER_DATE = "SELECT note FROM NoteModel AS note";
	public static final String GET_NOTES_BETWEEN_DATES = "SELECT note FROM NoteModel AS note";
	
	private NoteModelNamedQueries() {
	}
	
}
