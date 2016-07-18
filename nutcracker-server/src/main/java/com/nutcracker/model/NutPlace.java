package com.nutcracker.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Place")
public class NutPlace implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = 6499034323261719837L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int placeId;
	
	private String country;
	private String city;
	private String address;
	
	public NutPlace() {
	}

	public NutPlace(String country, String city, String address) {
		this.country = country;
		this.city = city;
		this.address = address;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
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
		return Objects.hash(this.country,
				this.city,
				this.address);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		
		if (this == obj) { 
			return true;
		}
		
		if (false == obj instanceof NutPlace) {
			return false;
		}
		
		NutPlace place = (NutPlace) obj;
		
		return this.country.equals(place.country) && 
				this.city.equals(place.city) && 
				this.address.equals(place.address);
	}
}
