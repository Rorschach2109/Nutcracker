package com.adrznej.nutcracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private List<NoteModel> userNotes;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<CategoryModel> userCategories;
	
	public UserModel() {
	}

	public UserModel(String userLogin, String userPassword) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.userNotes = new ArrayList<>();
		this.userCategories = new ArrayList<>();
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

	public List<NoteModel> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(List<NoteModel> userNotes) {
		this.userNotes = userNotes;
	}

	public List<CategoryModel> getUserCategories() {
		return userCategories;
	}

	public void setUserCategories(List<CategoryModel> userCategories) {
		this.userCategories = userCategories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.userLogin, this.userModelId);
	}

	@Override
	public boolean equals(Object obj) {
		if (false == obj instanceof UserModel) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		UserModel userModel = (UserModel) obj;
		return this.userLogin.equals(userModel.userLogin) &&
				this.userModelId == userModel.userModelId;
	}
}
