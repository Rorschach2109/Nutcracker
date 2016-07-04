package com.adrznej.nutcracker.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Note")
public class NoteModel implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = 9177390533227007889L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int noteModelId;
	
	@Column(nullable=false)
	private String message;

	public NoteModel() {
	}

	public NoteModel(String message) {
		this.message = message;
	}

	public int getNoteModelId() {
		return noteModelId;
	}

	public void setNoteModelId(int noteModelId) {
		this.noteModelId = noteModelId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.message);
	}

	@Override
	public boolean equals(Object obj) {
		if (false == obj instanceof NoteModel) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		NoteModel noteModel = (NoteModel) obj;
		return this.message.equals(noteModel.message);
	}
}
