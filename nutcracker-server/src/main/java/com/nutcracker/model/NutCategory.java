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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nutcracker.model.query.NutCategoryNamedQuery;

@Entity
@Table(name="Category")
@NamedQueries(
		value={
			@NamedQuery(name="categoryByNameAndUserId", 
				query=NutCategoryNamedQuery.CATEGORY_BY_NAME_AND_USER_ID)
		}
	)
public class NutCategory implements java.io.Serializable, Comparable<NutCategory> {

	@Transient
	private static final long serialVersionUID = -2454744019929491022L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;
	
	private String categoryName;
	
	@OneToMany(mappedBy="noteCategory", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<NutNote> categoryNotes;

	@ManyToOne
	private NutUser categoryOwner;
	
	public NutCategory() {
	}

	public NutCategory(String categoryName) {
		this.categoryName = categoryName;
		this.categoryNotes = new HashSet<>();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<NutNote> getCategoryNotes() {
		return categoryNotes;
	}

	public void setCategoryNotes(Set<NutNote> categoryNotes) {
		this.categoryNotes = categoryNotes;
	}

	public NutUser getCategoryOwner() {
		return categoryOwner;
	}

	public void setCategoryOwner(NutUser categoryOwner) {
		this.categoryOwner = categoryOwner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.categoryName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		
		if (this == obj) { 
			return true;
		}
		
		if (false == obj instanceof NutCategory) {
			return false;
		}
		
		NutCategory category = (NutCategory) obj;
		return this.categoryName.equals(category.categoryName);
	}
	
	@Override
	public String toString() {
		return this.categoryName;
	}

	@Override
	public int compareTo(NutCategory nutCategory) {
		return this.categoryName.compareTo(nutCategory.categoryName);
	}
}
