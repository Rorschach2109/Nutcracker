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
@Table(name="EPlace")
public class NotePlaceModel implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = 4964549122116459078L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventPlaceId;
	
	@Column(nullable=false)
	private String country;
	
	@Column(nullable=false)
	private String city;
	
	private String address;
	
	public NotePlaceModel() {
	}

	public NotePlaceModel(String country, String city, String address) {
		this.country = country;
		this.city = city;
		this.address = address;
	}

	public int getEventPlaceId() {
		return eventPlaceId;
	}

	public void setEventPlaceId(int eventPlaceId) {
		this.eventPlaceId = eventPlaceId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				this.country,
				this.city);
	}

	@Override
	public boolean equals(Object obj) {
		if (false == obj instanceof NotePlaceModel) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		NotePlaceModel eventPlaceModel = (NotePlaceModel) obj;
		return this.country.equals(eventPlaceModel.country) &&
				this.city.equals(eventPlaceModel.city);
	}
}
