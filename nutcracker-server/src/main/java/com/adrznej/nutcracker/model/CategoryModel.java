package com.adrznej.nutcracker.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Category")
public class CategoryModel implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = -5045672762996974706L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;
	
	private String categoryName;

	public CategoryModel() {
	}

	public CategoryModel(String categoryName) {
		this.categoryName = categoryName;
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

	@Override
	public int hashCode() {
		return Objects.hash(this.categoryName);
	}

	@Override
	public boolean equals(Object obj) {
		if (false == obj instanceof CategoryModel) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		CategoryModel categoryModel = (CategoryModel) obj;		
		return this.categoryName.equals(categoryModel.categoryName);
	}
	
	
}
