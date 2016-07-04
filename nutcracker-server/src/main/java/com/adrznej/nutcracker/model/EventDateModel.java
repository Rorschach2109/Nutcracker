package com.adrznej.nutcracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EDate")
public class EventDateModel implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventDateModelId;
	
	public EventDateModel() {
	}

	public int getEventDateModelId() {
		return eventDateModelId;
	}

	public void setEventDateModelId(int eventDateModelId) {
		this.eventDateModelId = eventDateModelId;
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
}
