package com.nutcracker.app.state;

import com.nutcracker.app.controller.NutMainController;

public class NutMainControllerFutureState implements INutMainControllerState {

	@Override
	public void updateLayoutList(NutMainController mainController) {
		mainController.generateFutureContent();
	}

}
