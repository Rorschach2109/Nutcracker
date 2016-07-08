package com.adrznej.nutcracker.query;

public final class UserModelNamedQueries {

	public static final String GET_ALL_USERS = "SELECT user FROM UserModel AS user";
	public static final String GET_USER_BY_LOGIN = "SELECT user FROM UserModel AS user WHERE user.userLogin = :userLogin";
	
	private UserModelNamedQueries() {
		
	}
}
