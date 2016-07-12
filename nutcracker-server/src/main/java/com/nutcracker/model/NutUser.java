package com.nutcracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="User", 
	uniqueConstraints={
		@UniqueConstraint(columnNames={"userLogin"})
}) 
public class NutUser implements java.io.Serializable {
	
	@Transient
	private static final long serialVersionUID = 714002025723723934L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	private String userLogin;
	private String userPassword;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="User_Category", 
		joinColumns={@JoinColumn(name="userId")}, 
		inverseJoinColumns={@JoinColumn(name="categoryId")}
	)
	private List<NutCategory> userCategories;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="User_Note", 
		joinColumns={@JoinColumn(name="userId")}, 
		inverseJoinColumns={@JoinColumn(name="noteId")}
	)
	private List<NutNote> userNotes;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="User_Place",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="placeId")}
	)
	private List<NutPlace> userPlaces;
	
	public NutUser() {
	}
	
	public NutUser(String userLogin, String userPassword) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.userCategories = new ArrayList<>();
		this.userNotes = new ArrayList<>();
		this.userPlaces = new ArrayList<>();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<NutCategory> getUserCategories() {
		return userCategories;
	}

	public void setUserCategories(List<NutCategory> userCategories) {
		this.userCategories = userCategories;
	}

	public List<NutPlace> getUserPlaces() {
		return userPlaces;
	}

	public void setUserPlaces(List<NutPlace> userPlaces) {
		this.userPlaces = userPlaces;
	}

	public List<NutNote> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(List<NutNote> userNotes) {
		this.userNotes = userNotes;
	}
}
