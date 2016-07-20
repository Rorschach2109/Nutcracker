package com.nutcracker.app.view;

public final class ResourcePathFinder {
	public final static String LOGIN_VIEW;
	public final static String MAIN_VIEW;
	
	static {
		LOGIN_VIEW = "view/LoginView.fxml";
		MAIN_VIEW = "view/NutcrackerMainView.fxml";
	}
	
	private ResourcePathFinder() {
		
	}
}
