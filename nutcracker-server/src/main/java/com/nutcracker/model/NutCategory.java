package com.nutcracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Category")
public class NutCategory implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = -2454744019929491022L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;
	
	private String categoryName;
	
	@OneToMany(mappedBy="noteCategory", cascade=CascadeType.REMOVE)
	private List<NutNote> categoryNotes;

	public NutCategory() {
	}

	public NutCategory(String categoryName) {
		this.categoryName = categoryName;
		this.categoryNotes = new ArrayList<>();
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

	public List<NutNote> getCategoryNotes() {
		return categoryNotes;
	}

	public void setCategoryNotes(List<NutNote> categoryNotes) {
		this.categoryNotes = categoryNotes;
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
}
