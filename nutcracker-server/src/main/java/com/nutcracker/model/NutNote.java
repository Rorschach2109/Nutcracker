package com.nutcracker.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nutcracker.model.converters.BooleanConverter;
import com.nutcracker.model.converters.LocalDateTimeConverter;
import com.nutcracker.model.query.NutNoteNamedQuery;

@Entity
@Table(name="Note")
@NamedQueries(
	value={
		@NamedQuery(name="noteWithDeadline", 
			query=NutNoteNamedQuery.NOTE_WITH_DEADLINE),
		@NamedQuery(name="noteByMessage",
			query=NutNoteNamedQuery.NOTE_BY_MESSAGE),
		@NamedQuery(name="noteByCategory",
			query=NutNoteNamedQuery.NOTE_BY_CATEGORY),
		@NamedQuery(name="noteByPlace",
			query=NutNoteNamedQuery.NOTE_BY_PLACE),
		@NamedQuery(name="noteOther",
			query=NutNoteNamedQuery.NOTE_OTHER)
	}
)
public class NutNote implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = -8923629796851397384L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int noteId;
	
	@Column(nullable=false, columnDefinition="TEXT")
	private String noteMessage;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private NutCategory noteCategory;

	@ManyToOne
	@JoinColumn(name="placeId")
	private NutPlace notePlace;
	
	@ManyToOne
	private NutUser noteOwner;
	
	@Convert(converter=LocalDateTimeConverter.class)
	private LocalDateTime noteDeadline;
	
	@Convert(converter=BooleanConverter.class)
	private boolean globalAvailable;
	
	public NutNote() {
	}
	
	public NutNote(String noteMessage) {
		this(noteMessage, false);
	}
	
	public NutNote(String noteMessage, boolean globalAvailable) {
		this.noteMessage = noteMessage;
		this.globalAvailable = globalAvailable;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteMessage() {
		return noteMessage;
	}

	public void setNoteMessage(String noteMessage) {
		this.noteMessage = noteMessage;
	}

	public NutCategory getNoteCategory() {
		return noteCategory;
	}

	public void setNoteCategory(NutCategory noteCategory) {
		this.noteCategory = noteCategory;
	}

	public LocalDateTime getNoteDeadline() {
		return noteDeadline;
	}

	public void setNoteDeadline(LocalDateTime noteDeadline) {
		this.noteDeadline = noteDeadline;
	}

	public NutUser getNoteOwner() {
		return noteOwner;
	}

	public void setNoteOwner(NutUser noteOwner) {
		this.noteOwner = noteOwner;
	}

	public boolean isGlobalAvailable() {
		return globalAvailable;
	}

	public void setGlobalAvailable(boolean globalAvailable) {
		this.globalAvailable = globalAvailable;
	}

	public NutPlace getNotePlace() {
		return notePlace;
	}

	public void setNotePlace(NutPlace notePlace) {
		this.notePlace = notePlace;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.globalAvailable,
				this.noteCategory,
				this.noteMessage);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		
		if (this == obj) { 
			return true;
		}
		
		if (false == obj instanceof NutNote) {
			return false;
		}
		
		NutNote note = (NutNote) obj;
		return this.globalAvailable == note.globalAvailable &&
				this.noteCategory.equals(note.noteCategory) &&
				this.noteMessage.equals(note.noteMessage);
	}
}
