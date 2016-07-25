package com.nutcracker.app.view;

public final class ResourcePathFinder {
	public final static String LOGIN_VIEW;
	public final static String MAIN_VIEW;
	public final static String NOTE_DETAILS_VIEW;
	
	static {
		LOGIN_VIEW = "view/LoginView.fxml";
		MAIN_VIEW = "view/NutcrackerMainView.fxml";
		NOTE_DETAILS_VIEW = "view/NoteDetailsView.fxml";
	}
	
	private ResourcePathFinder() {
		
	}
}
