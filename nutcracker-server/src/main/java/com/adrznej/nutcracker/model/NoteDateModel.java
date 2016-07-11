package com.adrznej.nutcracker.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="NoteDate")
public class NoteDateModel implements java.io.Serializable, java.lang.Comparable<NoteDateModel> {

	@Transient
	private static final long serialVersionUID = -5749909120595187094L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventDateModelId;
	
	LocalDateTime noteCreation;
	LocalDateTime noteDeadline;
	
	public NoteDateModel() {
	}

	public int getEventDateModelId() {
		return eventDateModelId;
	}

	public void setEventDateModelId(int eventDateModelId) {
		this.eventDateModelId = eventDateModelId;
	}
	
	public LocalDateTime getNoteCreation() {
		return noteCreation;
	}

	public void setNoteCreation(LocalDateTime noteCreation) {
		this.noteCreation = noteCreation;
	}

	public LocalDateTime getNoteDeadline() {
		return noteDeadline;
	}

	public void setNoteDeadline(LocalDateTime noteDeadline) {
		this.noteDeadline = noteDeadline;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int compareTo(NoteDateModel o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
