package com.nutcracker.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.nutcracker.model.query.NutUserNamedQuery;

@Entity
@Table(name="User", 
	uniqueConstraints={
		@UniqueConstraint(columnNames={"userLogin"})
})
@NamedQueries(
	value={
		@NamedQuery(name="userCountByLogin",
			query=NutUserNamedQuery.USER_COUNT_BY_LOGIN),
		@NamedQuery(name="userByLogin",
			query=NutUserNamedQuery.USER_BY_LOGIN),
		@NamedQuery(name="userAll",
			query=NutUserNamedQuery.USER_ALL)
	}
)
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
	private Set<NutCategory> userCategories;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="User_Note", 
		joinColumns={@JoinColumn(name="userId")}, 
		inverseJoinColumns={@JoinColumn(name="noteId")}
	)
	private Set<NutNote> userNotes;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="User_Place",
		joinColumns={@JoinColumn(name="userId")},
		inverseJoinColumns={@JoinColumn(name="placeId")}
	)
	private Set<NutPlace> userPlaces;
	
	public NutUser() {
	}
	
	public NutUser(String userLogin, String userPassword) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.userCategories = new HashSet<>();
		this.userNotes = new HashSet<>();
		this.userPlaces = new HashSet<>();
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

	public Set<NutCategory> getUserCategories() {
		return userCategories;
	}

	public void setUserCategories(Set<NutCategory> userCategories) {
		this.userCategories = userCategories;
	}

	public Set<NutPlace> getUserPlaces() {
		return userPlaces;
	}

	public void setUserPlaces(Set<NutPlace> userPlaces) {
		this.userPlaces = userPlaces;
	}

	public Set<NutNote> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(Set<NutNote> userNotes) {
		this.userNotes = userNotes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.userLogin);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		
		if (this == obj) { 
			return true;
		}
		
		if (false == obj instanceof NutUser) {
			return false;
		}
		
		NutUser user = (NutUser) obj;
		return this.userLogin.equals(user.userLogin);
	}
}
