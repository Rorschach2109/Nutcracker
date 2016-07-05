package com.adrznej.nutcracker.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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

	@Column(nullable=false)
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="Event_Person",
			joinColumns={@JoinColumn(name="EventId")},
			inverseJoinColumns={@JoinColumn(name="PersonId")})
	private Set<EventPersonModel> eventPeople;
	
	@Column(nullable=false)
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="EPlaceId")
	private EventPlaceModel eventPlace;

	@Column(nullable=false)
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private NoteModel eventNote;
	
	public EventModel() {
	}

	public EventModel(Set<EventPersonModel> eventPeople, 
			EventPlaceModel eventPlace, 
			NoteModel eventNote) {
		this.eventPeople = eventPeople;
		this.eventPlace = eventPlace;
		this.eventNote = eventNote;
	}

	public int getEventModelId() {
		return eventModelId;
	}

	public void setEventModelId(int eventModelId) {
		this.eventModelId = eventModelId;
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

	public NoteModel getEventNote() {
		return eventNote;
	}

	public void setEventNote(NoteModel eventNote) {
		this.eventNote = eventNote;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.eventPlace,
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
				this.eventNote.equals(eventModel.eventNote);
	}
}
