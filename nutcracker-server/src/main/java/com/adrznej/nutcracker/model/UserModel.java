package com.adrznej.nutcracker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.adrznej.nutcracker.query.UserModelNamedQueries;

@Entity
@Table(name="User",
		uniqueConstraints={@UniqueConstraint(columnNames={"userLogin"})})
@NamedQueries({
	@NamedQuery(name="getAllUsers",
			query=UserModelNamedQueries.GET_ALL_USERS),
	@NamedQuery(name="getUserByLogin",
			query=UserModelNamedQueries.GET_USER_BY_LOGIN)
	})
public class UserModel implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = 9018566845431851167L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userModelId;
	
	private String userLogin;
	private String userPassword;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="UserNoteId")
	private Set<NoteModel> userNotes;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private Set<CategoryModel> userCategories;
	
	public UserModel() {
	}

	public UserModel(String userLogin, String userPassword) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.userNotes = null;
	}

	public int getUserModelId() {
		return userModelId;
	}

	public void setUserModelId(int userModelId) {
		this.userModelId = userModelId;
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

	public Set<NoteModel> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(Set<NoteModel> userNotes) {
		this.userNotes = userNotes;
	}

	public Set<CategoryModel> getUserCategories() {
		return userCategories;
	}

	public void setUserCategories(Set<CategoryModel> userCategories) {
		this.userCategories = userCategories;
	}
}
