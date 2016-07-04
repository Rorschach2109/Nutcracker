package com.adrznej.nutcracker.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Event")
public class EventModel implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = -8850054833889934168L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventModelId;

	@Column(name="EParent", nullable=true)
	private EventModel eventParent;
	
	@Column(name="EPeople", nullable=false)
	private Set<EventPersonModel> eventPeople;
	
	@Column(name="EPlace", nullable=false)
	private EventPlaceModel eventPlace;

	@Column(name="EDate", nullable=false)
	private EventDateModel eventDate;
	
	@Column(name="ENote", nullable=false)
	private NoteModel eventNote;
	
	public EventModel() {
	}

	public EventModel(EventModel eventParent,
			Set<EventPersonModel> eventPeople, 
			EventPlaceModel eventPlace, 
			EventDateModel eventDate,
			NoteModel eventNote) {
		this.eventParent = eventParent;
		this.eventPeople = eventPeople;
		this.eventPlace = eventPlace;
		this.eventDate = eventDate;
		this.eventNote = eventNote;
	}

	public int getEventModelId() {
		return eventModelId;
	}

	public void setEventModelId(int eventModelId) {
		this.eventModelId = eventModelId;
	}
	
	public EventModel getEventParent() {
		return eventParent;
	}
	
	public void setEventParent(EventModel eventParent) {
		this.eventParent = eventParent;
	}

	public Set<EventPersonModel> getEventPeople() {
		return eventPeople;
	}

	public void setEventPeople(Set<EventPersonModel> eventPeople) {
		this.eventPeople = eventPeople;
	}

	public EventPlaceModel getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(EventPlaceModel eventPlace) {
		this.eventPlace = eventPlace;
	}

	public EventDateModel getEventDate() {
		return eventDate;
	}

	public void setEventDate(EventDateModel eventDate) {
		this.eventDate = eventDate;
	}

	public NoteModel getEventNote() {
		return eventNote;
	}

	public void setEventNote(NoteModel eventNote) {
		this.eventNote = eventNote;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.eventPlace,
				this.eventDate,
				this.eventNote);
	}

	@Override
	public boolean equals(Object obj) {
		if (false == obj instanceof EventModel) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		EventModel eventModel = (EventModel) obj;
		return this.eventPlace.equals(eventModel.eventPlace) &&
				this.eventDate.equals(eventModel.eventDate) &&
				this.eventNote.equals(eventModel.eventNote);
	}
}
