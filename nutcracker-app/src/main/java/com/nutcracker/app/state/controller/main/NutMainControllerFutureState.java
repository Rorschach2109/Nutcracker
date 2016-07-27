package com.nutcracker.app.state.controller.main;

import com.nutcracker.app.controller.NutMainController;
import com.nutcracker.model.NutNote;

public class NutMainControllerFutureState implements INutMainControllerState {

	@Override
	public void updateLayoutList(NutMainController mainController) {
		mainController.generateFutureContent();
	}

	@Override
	public <T> void layoutListDoubleClickHandler(NutMainController mainController, T object) {
		mainController.showReminderDetailsWindow((NutNote) object);
	}
}
