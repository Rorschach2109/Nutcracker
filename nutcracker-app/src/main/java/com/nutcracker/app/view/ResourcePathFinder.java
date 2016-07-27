package com.nutcracker.app.view;

public final class ResourcePathFinder {
	public final static String CATEGORY_DETAILS_VIEW;
	public final static String LOGIN_VIEW;
	public final static String MAIN_VIEW;
	public final static String NOTE_DETAILS_VIEW;
	public final static String REMINDER_DETAILS_VIEW;
		
	static {
		CATEGORY_DETAILS_VIEW = "view/CategoryDetailsView.fxml";
		LOGIN_VIEW = "view/LoginView.fxml";
		MAIN_VIEW = "view/NutcrackerMainView.fxml";
		NOTE_DETAILS_VIEW = "view/NoteDetailsView.fxml";
		REMINDER_DETAILS_VIEW = "view/ReminderDetailsView.fxml";
	}
	
	private ResourcePathFinder() {
		
	}
}
