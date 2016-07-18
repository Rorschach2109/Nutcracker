package com.nutcracker.model.query;

public final class NutUserNamedQuery {

	public static final String USER_COUNT_BY_LOGIN = "SELECT COUNT(user) FROM NutUser user WHERE user.userLogin = :userLogin";
	
	private NutUserNamedQuery() {
		
	}
}
