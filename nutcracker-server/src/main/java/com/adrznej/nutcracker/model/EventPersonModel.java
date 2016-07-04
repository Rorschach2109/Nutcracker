package com.adrznej.nutcracker.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="EPerson",
		uniqueConstraints={@UniqueConstraint(columnNames={"name", "surname"})})
public class EventPersonModel implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = -8025435782836032455L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventPersonModelId;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	private String nick;
	private String telephoneNumber;
	
	public EventPersonModel() {
	}

	public EventPersonModel(String name, String surname, String nick, String telephoneNumber) {
		this.name = name;
		this.surname = surname;
		this.nick = nick;
		this.telephoneNumber = telephoneNumber;
	}

	public int getEventPersonModelId() {
		return eventPersonModelId;
	}

	public void setEventPersonModelId(int eventPersonModelId) {
		this.eventPersonModelId = eventPersonModelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				this.name,
				this.surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (false == obj instanceof EventPersonModel) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		EventPersonModel eventPersonModel = (EventPersonModel) obj;
		return this.name.equals(eventPersonModel.name) &&
				this.surname.equals(eventPersonModel.surname);
	}
}
