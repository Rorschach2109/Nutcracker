package com.nutcracker.app.state;

import com.nutcracker.app.controller.NutMainController;

public class NutMainControllerPastState implements INutMainControllerState {
	
	@Override
	public void updateLayoutList(NutMainController mainController) {
		mainController.generatePastContent();;
	}
}
