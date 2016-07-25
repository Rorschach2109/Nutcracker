package com.nutcracker.model.query;

public final class NutUserNamedQuery {

	public static final String USER_COUNT_BY_LOGIN = "SELECT COUNT(user) FROM NutUser user WHERE user.userLogin = :userLogin";
	public static final String USER_BY_LOGIN = "SELECT user FROM NutUser user WHERE user.userLogin = :userLogin";
	public static final String USER_ALL = "SELECT user FROM NutUser user";
	
	private NutUserNamedQuery() {
		
	}
}
