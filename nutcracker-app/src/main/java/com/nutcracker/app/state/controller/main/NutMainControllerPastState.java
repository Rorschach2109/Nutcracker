package com.nutcracker.app.state.controller.main;

import com.nutcracker.app.controller.NutMainController;
import com.nutcracker.model.NutNote;

public class NutMainControllerPastState implements INutMainControllerState {
	
	@Override
	public void updateLayoutList(NutMainController mainController) {
		mainController.generatePastContent();;
	}
	
	@Override
	public <T> void layoutListDetailsHandler(NutMainController mainController, T object) {
		mainController.showReminderDetailsWindow((NutNote) object);
	}
	
	@Override
	public <T> void layoutListEditButtonHandler(NutMainController mainController, T object) {
		mainController.showEditReminderWindow((NutNote) object);
	}
}
