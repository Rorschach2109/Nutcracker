package com.nutcracker.app.view;

public final class ResourcePathFinder {
	public final static String LOGIN_VIEW;
	public final static String MAIN_VIEW;
	public final static String REMINDER_ADD_VIEW;
	public final static String CATEGORY_ADD_VIEW;
	
	static {
		LOGIN_VIEW = "view/LoginView.fxml";
		MAIN_VIEW = "view/NutcrackerMainView.fxml";
		REMINDER_ADD_VIEW = "view/ReminderAddView.fxml";
		CATEGORY_ADD_VIEW = "view/CategoryAddView.fxml";
	}
	
	private ResourcePathFinder() {
		
	}
}
