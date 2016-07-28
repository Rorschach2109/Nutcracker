package com.nutcracker.model.query;

public class NutCategoryNamedQuery {

	public static final String CATEGORY_BY_NAME_AND_USER_ID = "SELECT category FROM NutCategory category JOIN FETCH category.categoryOwner owner WHERE category.categoryName = :categoryName AND owner.userId = :userId";
	
	private NutCategoryNamedQuery() {
		
	}
}
